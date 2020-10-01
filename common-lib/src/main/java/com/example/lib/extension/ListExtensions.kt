package com.example.lib.extension

/**
 * Returns second element.
 * @throws [NoSuchElementException] if the list is empty or just has an element
 */
fun <T> List<T>.second(): T {
    if (isEmpty()) {
        throw NoSuchElementException("List is empty.")
    }
    if (size < 2) {
        throw NoSuchElementException("List just has an element")
    }
    return this[1]
}

/**
 * Returns second element.
 * @return null if the list is empty or just has an element
 */
fun <T> List<T>.secondOrNull(): T? {
    if (isEmpty() || size < 2) {
        return null
    }
    return this[1]
}