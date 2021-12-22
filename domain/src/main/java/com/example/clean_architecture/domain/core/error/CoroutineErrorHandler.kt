package com.example.clean_architecture.domain.core.error

interface CoroutineErrorHandler {
    fun handleException(exception: Exception): CoroutineError
}