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
                val code = exception.code().default(-1)
                val errorMessage = exception.response()
                    ?.errorBody()
                    ?.string()
                    ?.let { errorBody ->
                        try {
                            val serverErrorResponse =
                                Gson().fromJson(errorBody, ServerErrorResponse::class.java)
                            return@let serverErrorResponse.message
                        } catch (parseException: Exception) {
                            Timber.e(parseException)
                            return@let null
                        }
                    }
                return ApiError.ServerError(
                    code = code,
                    errorMessage = errorMessage.defaultEmpty()
                )
            }
            else -> {
                ApiError.UnknownError(exception)
            }
        }
    }
}