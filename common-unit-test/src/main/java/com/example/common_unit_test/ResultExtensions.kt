package com.example.common_unit_test

import com.example.lib.result.ResultWrapper
import org.junit.Assert

fun <R> ResultWrapper<R>.assertSuccess(expected: ResultWrapper.Success<R>) {
    subscribe(
        success = { data ->
            Assert.assertEquals(expected.data, data)
        },
        error = {
            Assert.assertTrue(false)
        }
    )
}

fun <R> ResultWrapper<R>.assertError(expected: ResultWrapper.Error) {
    subscribe(
        success = {
            Assert.assertTrue(false)
        },
        error = { coroutineException ->
            Assert.assertEquals(expected.coroutineException, coroutineException)
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