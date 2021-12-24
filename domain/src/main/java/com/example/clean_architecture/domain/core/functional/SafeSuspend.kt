package com.example.clean_architecture.domain.core.functional

import com.example.clean_architecture.domain.core.error.DefaultFailure
import com.example.clean_architecture.domain.core.error.Failure
import com.example.clean_architecture.domain.core.error.FailureHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

suspend fun <R> safeSuspend(
    failureHandler: FailureHandler? = null,
    action: suspend () -> Either<Failure, R>
): Either<Failure, R> = try {
    action()
} catch (exception: Exception) {
    val failure = failureHandler?.handleThrowable(exception)
    if (failure != null) {
        Either.Left(failure)
    } else {
        Either.Left(DefaultFailure(exception))
    }
}

suspend fun safeSuspendIgnoreFailure(
    action: suspend () -> Unit
) = try {
    action()
} catch (exception: Exception) {
    // ignore
}

fun <T> Flow<T>.toEither(
    failureHandler: FailureHandler? = null
): Flow<Either<Failure, T>> = map {
    Either.Right(it) as Either<Failure, T>
}.catch { exception ->
    failureHandler?.handleThrowable(exception)?.let {
        emit(Either.Left(it))
    }
}