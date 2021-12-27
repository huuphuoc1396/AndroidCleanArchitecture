package com.example.clean_architecture.domain.core.error

interface FailureHandler {
    fun handleThrowable(throwable: Throwable): Failure
}