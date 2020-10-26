package com.example.common_unit_test

import com.example.common_lib.exception.CoroutineException
import com.example.common_lib.result.ResultWrapper
import org.junit.Assert

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

fun <R> ResultWrapper<R>.assertError(expected: CoroutineException) {
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