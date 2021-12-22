package com.example.clean_architecture.domain.core.interactor

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class UseCase<Params : UseCaseParams, Result>(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    protected abstract suspend fun executeInternal(params: Params): Result

    suspend operator fun invoke(params: Params): Result {
        return withContext(dispatcher) { executeInternal(params) }
    }
}