package com.example.clean_architecture.domain.core.interactor.usecase

import com.example.clean_architecture.domain.core.interactor.params.UseCaseParams
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class CoroutineUseCase<Params : UseCaseParams, Result>(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    protected abstract suspend fun execute(params: Params): Result

    suspend operator fun invoke(params: Params): Result {
        return withContext(dispatcher) { execute(params) }
    }
}