package com.example.clean_architecture.core_lib.exception

data class DefaultCoroutineException(
    val exception: Exception
) : CoroutineException