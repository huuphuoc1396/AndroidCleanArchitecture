package com.example.lib

import java.util.*

fun String?.orEmpty(): String {
    return this.or("")
}

fun Int?.orZero(): Int {
    return this.or(0)
}

fun Long?.orZero(): Long {
    return this.or(0)
}

fun Double?.orZero(): Double {
    return this.or(0.0)
}

fun Float?.orZero(): Float {
    return this.or(0.0f)
}

fun <T> List<T>?.orEmpty(): List<T> {
    return this ?: listOf()
}

fun Date?.orToday(): Date {
    return this.or(Date())
}

fun Boolean?.orFalse(): Boolean {
    return this.or(false)
}

fun Boolean?.orTrue(): Boolean {
    return this.or(true)
}

fun <T> T?.or(default: T): T {
    return this ?: default
}