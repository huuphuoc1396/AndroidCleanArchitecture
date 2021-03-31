package com.example.clean_architecture.core_lib.result

import com.example.clean_architecture.core_lib.error.CoroutineError
import com.example.clean_architecture.core_lib.error.CoroutineErrorHandler
import com.example.clean_architecture.core_lib.error.DefaultCoroutineError

sealed class ResultWrapper<out R> {
    data class Success<out T>(val data: T) : ResultWrapper<T>()
    data class Error(
        val coroutineError: CoroutineError
    ) : ResultWrapper<Nothing>()

    val isSuccess get() = this is Success
    val isError get() = this is Error

    fun subscribe(success: (R) -> Unit, error: (CoroutineError) -> Unit) {
        when (this) {
            is Success -> success(this.data)
            is Error -> error(this.coroutineError)
        }
    }

    companion object {
        suspend fun <R> safeSuspend(
            coroutineErrorHandler: CoroutineErrorHandler? = null,
            action: suspend () -> ResultWrapper<R>
        ): ResultWrapper<R> {
            try {
                return action()
            } catch (exception: Exception) {
                val coroutineError = coroutineErrorHandler?.handleException(exception)
                if (coroutineError != null) {
                    return Error(coroutineError)
                }
                return Error(DefaultCoroutineError(exception))
            }
        }
    }
}