package com.example.clean_architecture.domain.core.extension

import kotlin.random.Random

private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
fun Random.nextString(length: Int = nextInt(1, 256)): String {
    return (1..length)
        .map { nextInt(0, charPool.size) }
        .map { index -> charPool[index] }
        .joinToString("")
}

fun Random.nextChar(): Char = nextInt(0, 255).toChar()