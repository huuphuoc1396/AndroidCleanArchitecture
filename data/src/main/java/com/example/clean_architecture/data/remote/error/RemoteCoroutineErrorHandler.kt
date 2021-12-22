package com.example.clean_architecture.data.remote.error

import com.example.clean_architecture.domain.core.error.ApiError
import com.example.clean_architecture.domain.core.error.CoroutineError
import com.example.clean_architecture.domain.core.error.CoroutineErrorHandler
import com.example.clean_architecture.domain.core.extension.default
import com.example.clean_architecture.domain.core.extension.defaultEmpty
import com.example.clean_architecture.data.remote.response.ServerErrorResponse
import com.google.gson.Gson
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class RemoteCoroutineErrorHandler @Inject constructor() : CoroutineErrorHandler {
    override fun handleException(exception: Exception): CoroutineError {
        return when (exception) {
            is IOException -> {
                ApiError.ConnectionError
            }
            is HttpException -> {
                handleHttpException(exception)
            }
            else -> {
                ApiError.UnknownError(exception)
            }
        }
    }

    private fun handleHttpException(httpException: HttpException): ApiError.ServerError {
        val code = httpException.code().default(-1)
        val errorBody = httpException.response()?.errorBody()?.string()
        val errorMessage = try {
            val serverErrorResponse = Gson().fromJson(errorBody, ServerErrorResponse::class.java)
            serverErrorResponse.message.defaultEmpty()
        } catch (parseException: Exception) {
            Timber.e(parseException)
            ""
        }
        return ApiError.ServerError(
            code = code,
            errorMessage = errorMessage
        )
    }
}