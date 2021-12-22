package com.example.clean_architecture.core_unit_test

import com.example.clean_architecture.domain.core.error.CoroutineError
import com.example.clean_architecture.domain.core.result.ResultWrapper

fun <R> ResultWrapper<R>.assertSuccess(expected: R) {
    subscribe(
        success = { actual ->
            Assert.assertEquals(expected, actual)
        },
        error = {
            Assert.assertTrue(false)
        }
    )
}

fun <R> ResultWrapper<R>.assertError(expected: CoroutineError) {
    subscribe(
        success = {
            Assert.assertTrue(false)
        },
        error = { actual ->
            Assert.assertEquals(expected, actual)
        }
    )
}

fun <R> ResultWrapper<R>.assertSuccess() {
    subscribe(
        success = {
            Assert.assertTrue(true)
        },
        error = {
            Assert.assertTrue(false)
        }
    )
}

fun <R> ResultWrapper<R>.assertError() {
    subscribe(
        success = {
            Assert.assertTrue(false)
        },
        error = {
            Assert.assertTrue(true)
        }
    )
}