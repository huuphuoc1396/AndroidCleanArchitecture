package com.example.clean_architecture.domain.core.error

data class DefaultFailure(
    val exception: Exception,
) : Failure