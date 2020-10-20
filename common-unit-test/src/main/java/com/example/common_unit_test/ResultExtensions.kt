package com.example.common_unit_test

import com.example.lib.result.Result
import org.junit.Assert

fun <R> Result<R>.assertSuccess(expected: Result.Success<R>) {
    subscribe(
        success = { data ->
            Assert.assertEquals(expected.data, data)
        },
        error = {
            Assert.assertTrue(false)
        }
    )
}

fun <R> Result<R>.assertError(expected: Result.Error) {
    subscribe(
        success = {
            Assert.assertTrue(false)
        },
        error = { coroutineException ->
            Assert.assertEquals(expected.coroutineException, coroutineException)
        }
    )
}

fun <R> Result<R>.assertSuccess() {
    subscribe(
        success = {
            Assert.assertTrue(true)
        },
        error = {
            Assert.assertTrue(false)
        }
    )
}

fun <R> Result<R>.assertError() {
    subscribe(
        success = {
            Assert.assertTrue(false)
        },
        error = {
            Assert.assertTrue(true)
        }
    )
}