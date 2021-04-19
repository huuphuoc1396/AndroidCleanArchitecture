package com.example.clean_architecture.core_lib.result

import com.example.clean_architecture.core_lib.error.CoroutineError
import com.example.clean_architecture.core_lib.error.CoroutineErrorHandler
import com.example.clean_architecture.core_lib.error.DefaultCoroutineError

sealed class ResultWrapper<out R> {
    data class Success<out T>(val data: T) : ResultWrapper<T>()
    data class Error(
        val coroutineError: CoroutineError
    ) : ResultWrapper<Nothing>()

    val isSuccess: Boolean
        get() = this is Success

    val isError: Boolean
        get() = this is Error

    fun subscribe(success: (R) -> Unit, error: (CoroutineError) -> Unit) {
        when (this) {
            is Success -> success(data)
            is Error -> error(coroutineError)
        }
    }

    fun <T> map(transform: (R) -> T): ResultWrapper<T> {
        return when (this) {
            is Success -> Success(transform(data))
            is Error -> this
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