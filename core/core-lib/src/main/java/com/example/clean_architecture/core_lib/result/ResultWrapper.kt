package com.example.clean_architecture.core_lib.result

import com.example.clean_architecture.core_lib.exception.CoroutineException
import com.example.clean_architecture.core_lib.exception.CoroutineExceptionHandler
import com.example.clean_architecture.core_lib.exception.DefaultCoroutineException

sealed class ResultWrapper<out R> {
    data class Success<out T>(val data: T) : ResultWrapper<T>()
    data class Error(
        val coroutineException: CoroutineException
    ) : ResultWrapper<Nothing>()

    val isSuccess get() = this is Success
    val isError get() = this is Error

    fun subscribe(success: (R) -> Unit, error: (CoroutineException) -> Unit) {
        when (this) {
            is Success -> success(this.data)
            is Error -> error(this.coroutineException)
        }
    }

    companion object {
        suspend fun <R> safeSuspend(
            coroutineExceptionHandler: CoroutineExceptionHandler? = null,
            action: suspend () -> ResultWrapper<R>
        ): ResultWrapper<R> {
            try {
                return action()
            } catch (exception: Exception) {
                val coroutineException = coroutineExceptionHandler?.handleException(exception)
                if (coroutineException != null) {
                    return Error(coroutineException)
                }
                return Error(DefaultCoroutineException(exception))
            }
        }
    }
}