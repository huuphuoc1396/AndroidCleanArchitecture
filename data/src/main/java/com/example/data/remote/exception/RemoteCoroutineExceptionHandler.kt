package com.example.data.remote.exception

import com.example.common_lib.exception.ApiException
import com.example.common_lib.exception.CoroutineException
import com.example.common_lib.exception.CoroutineExceptionHandler
import com.example.common_lib.extension.default
import com.example.common_lib.extension.defaultEmpty
import com.example.data.remote.response.ServerErrorResponse
import com.google.gson.Gson
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class RemoteCoroutineExceptionHandler(
    private val gson: Gson
) : CoroutineExceptionHandler {
    override fun handleException(exception: Exception): CoroutineException {
        return when (exception) {
            is IOException -> {
                ApiException.ConnectionException
            }
            is HttpException -> {
                val code = exception.code().default(-1)
                val errorMessage = exception.response()?.errorBody()?.string()?.let { errorBody ->
                    try {
                        val serverErrorResponse = gson.fromJson<ServerErrorResponse>(
                            errorBody,
                            ServerErrorResponse::class.java
                        )
                        return@let serverErrorResponse.message
                    } catch (parseException: Exception) {
                        Timber.e(parseException)
                        return@let null
                    }
                }
                return ApiException.ServerException(
                    code = code,
                    errorMessage = errorMessage.defaultEmpty()
                )
            }
            else -> {
                ApiException.UnknownException(exception)
            }
        }
    }
}