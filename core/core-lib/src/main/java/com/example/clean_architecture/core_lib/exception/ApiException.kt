package com.example.clean_architecture.core_lib.exception

sealed class ApiException : CoroutineException {

    object ConnectionException : ApiException()

    data class ServerException(
        val code: Int,
        val errorMessage: String,
    ) : ApiException()

    data class UnknownException(
        val exception: Exception,
    ) : ApiException()
}