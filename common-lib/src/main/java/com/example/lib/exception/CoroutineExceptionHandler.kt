package com.example.lib.exception

interface CoroutineExceptionHandler {
    fun handleException(exception: Exception): CoroutineException
}