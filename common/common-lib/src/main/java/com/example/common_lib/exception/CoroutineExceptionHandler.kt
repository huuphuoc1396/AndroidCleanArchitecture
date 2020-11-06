package com.example.common_lib.exception

interface CoroutineExceptionHandler {
    fun handleException(exception: Exception): CoroutineException
}