package com.example.clean_architecture.domain.core.interactor.usecase

import com.example.clean_architecture.domain.core.interactor.params.UseCaseParams

abstract class BasicUseCase<Params : UseCaseParams, Result> : UseCase<Params, Result> {

    protected abstract fun execute(params: Params): Result

    operator fun invoke(params: Params): Result {
        return execute(params)
    }
}