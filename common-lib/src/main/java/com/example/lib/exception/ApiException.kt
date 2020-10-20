package com.example.lib.exception

sealed class ApiException : CoroutineException {

    object ConnectionException : ApiException()

    data class ServerException(
        val code: Int,
        val errorMessage: String
    ) : ApiException()

    data class UnknownException(
        val exception: Exception
    ) : ApiException()
}