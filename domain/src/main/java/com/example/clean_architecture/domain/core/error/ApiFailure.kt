package com.example.clean_architecture.domain.core.error

sealed class ApiFailure : Failure {

    object Connection : ApiFailure()

    data class Server(
        val code: Int,
        val errorMessage: String,
    ) : ApiFailure()

    data class Unknown(
        val exception: Exception,
    ) : ApiFailure()
}