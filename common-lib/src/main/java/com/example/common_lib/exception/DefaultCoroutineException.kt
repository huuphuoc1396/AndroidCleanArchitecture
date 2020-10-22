package com.example.common_lib.exception

data class DefaultCoroutineException(
    val exception: Exception
) : CoroutineException