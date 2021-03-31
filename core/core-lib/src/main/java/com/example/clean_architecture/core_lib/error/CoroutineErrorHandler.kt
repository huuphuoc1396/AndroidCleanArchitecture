package com.example.clean_architecture.core_lib.error

interface CoroutineErrorHandler {
    fun handleException(exception: Exception): CoroutineError
}