package com.example.clean_architecture.core_lib.usecase

abstract class UseCase<Params : UseCase.Params, Result> {

    protected abstract suspend fun executeInternal(params: Params): Result

    suspend fun execute(params: Params): Result {
        return executeInternal(params)
    }

    abstract class Params

    object EmptyParams : Params()
}