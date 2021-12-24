package com.example.clean_architecture.domain.core.error

interface FailureHandler {
    fun handleException(exception: Exception): Failure
}