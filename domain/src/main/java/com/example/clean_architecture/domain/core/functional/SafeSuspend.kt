package com.example.clean_architecture.domain.core.functional

import com.example.clean_architecture.domain.core.error.DefaultFailure
import com.example.clean_architecture.domain.core.error.Failure
import com.example.clean_architecture.domain.core.error.FailureHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

suspend fun <R> safeSuspend(
    failureHandler: FailureHandler? = null,
    action: suspend () -> ResultWrapper<Failure, R>
): ResultWrapper<Failure, R> = try {
    action()
} catch (exception: Exception) {
    val failure = failureHandler?.handleThrowable(exception)
    if (failure != null) {
        ResultWrapper.Failure(failure)
    } else {
        ResultWrapper.Failure(DefaultFailure(exception))
    }
}

suspend fun safeSuspendIgnoreFailure(
    action: suspend () -> Unit
) = try {
    action()
} catch (exception: Exception) {
    // ignore
}

@Suppress("USELESS_CAST")
fun <T> Flow<T>.resultWrapper(
    failureHandler: FailureHandler? = null
): Flow<ResultWrapper<Failure, T>> = map {
    ResultWrapper.Success(it) as ResultWrapper<Failure, T>
}.catch { exception ->
    failureHandler?.handleThrowable(exception)?.let {
        emit(ResultWrapper.Failure(it))
    }
}