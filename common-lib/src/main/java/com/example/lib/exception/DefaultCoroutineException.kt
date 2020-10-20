package com.example.lib.exception

data class DefaultCoroutineException(
    val exception: Exception
) : CoroutineException