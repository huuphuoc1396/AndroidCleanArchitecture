package com.example.clean_architecture.data.remote.error

import com.example.clean_architecture.core_lib.error.ApiError
import com.example.clean_architecture.core_lib.error.CoroutineError
import com.example.clean_architecture.core_lib.error.CoroutineErrorHandler
import com.example.clean_architecture.core_lib.extension.default
import com.example.clean_architecture.core_lib.extension.defaultEmpty
import com.example.clean_architecture.data.remote.response.ServerErrorResponse
import com.google.gson.Gson
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class RemoteCoroutineErrorHandler : CoroutineErrorHandler {
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