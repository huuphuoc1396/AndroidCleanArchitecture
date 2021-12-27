package com.example.clean_architecture.data.remote.error

import com.example.clean_architecture.data.remote.response.ServerErrorResponse
import com.example.clean_architecture.domain.core.error.ApiFailure
import com.example.clean_architecture.domain.core.error.FailureHandler
import com.example.clean_architecture.domain.core.extension.default
import com.example.clean_architecture.domain.core.extension.defaultEmpty
import com.google.gson.Gson
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class RemoteFailureHandler @Inject constructor() : FailureHandler {
    override fun handleThrowable(throwable: Throwable) = when (throwable) {
        is IOException -> {
            ApiFailure.Connection
        }
        is HttpException -> {
            handleHttpException(throwable)
        }
        else -> {
            ApiFailure.Unknown(throwable)
        }
    }

    private fun handleHttpException(httpException: HttpException): ApiFailure.Server {
        val code = httpException.code().default(-1)
        val errorBody = httpException.response()?.errorBody()?.string()
        val errorMessage = try {
            val serverErrorResponse = Gson().fromJson(errorBody, ServerErrorResponse::class.java)
            serverErrorResponse.message.defaultEmpty()
        } catch (parseException: Exception) {
            Timber.e(parseException)
            ""
        }
        return ApiFailure.Server(
            code = code,
            errorMessage = errorMessage
        )
    }
}