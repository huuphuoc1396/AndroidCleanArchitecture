package com.example.clean_architecture.core_lib.exception

interface CoroutineExceptionHandler {
    fun handleException(exception: Exception): CoroutineException
}