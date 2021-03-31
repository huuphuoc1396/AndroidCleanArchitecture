package com.example.clean_architecture.core_lib.error

data class DefaultCoroutineError(
    val exception: Exception,
) : CoroutineError