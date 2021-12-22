package com.example.clean_architecture.domain.core.error

data class DefaultCoroutineError(
    val exception: Exception,
) : CoroutineError