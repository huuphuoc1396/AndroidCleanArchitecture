package com.example.domain

abstract class UseCase<Params : UseCase.Params, Result>() {

    abstract suspend fun execute(params: Params): Result

    abstract class Params {
        object Empty
    }
}