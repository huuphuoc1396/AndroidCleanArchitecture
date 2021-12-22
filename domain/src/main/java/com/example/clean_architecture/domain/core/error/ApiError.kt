package com.example.clean_architecture.domain.core.error

sealed class ApiError : CoroutineError {

    object ConnectionError : ApiError()

    data class ServerError(
        val code: Int,
        val errorMessage: String,
    ) : ApiError()

    data class UnknownError(
        val exception: Exception,
    ) : ApiError()
}