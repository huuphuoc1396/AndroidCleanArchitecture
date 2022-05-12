/**
 * Copyright (C) 2019 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.clean_architecture.domain.core.functional

import com.example.clean_architecture.domain.core.functional.ResultWrapper.Failure
import com.example.clean_architecture.domain.core.functional.ResultWrapper.Success

/**
 * Represents a value of one of two possible types (a disjoint union).
 * Instances of [ResultWrapper] are either an instance of [Failure] or [Success].
 * FP Convention dictates that [Failure] is used for "failure"
 * and [Success] is used for "success".
 *
 * @see Failure
 * @see Success
 */
sealed class ResultWrapper<out L, out R> {
    /** * Represents the left side of [ResultWrapper] class which by convention is a "Failure". */
    data class Failure<out L>(val a: L) : ResultWrapper<L, Nothing>()

    /** * Represents the right side of [ResultWrapper] class which by convention is a "Success". */
    data class Success<out R>(val b: R) : ResultWrapper<Nothing, R>()

    /**
     * Returns true if this is a Right, false otherwise.
     * @see Success
     */
    val isSuccess get() = this is Success<R>

    /**
     * Returns true if this is a Left, false otherwise.
     * @see Failure
     */
    val isFailure get() = this is Failure<L>

    /**
     * Creates a Left type.
     * @see Failure
     */
    fun <L> left(a: L) = Failure(a)


    /**
     * Creates a Left type.
     * @see Success
     */
    fun <R> right(b: R) = Success(b)

    /**
     * Applies fnL if this is a Left or fnR if this is a Right.
     * @see Failure
     * @see Success
     */
    fun fold(fnL: (L) -> Unit, fnR: (R) -> Unit): Unit = when (this) {
        is Failure -> fnL(a)
        is Success -> fnR(b)
    }
}

/**
 * Composes 2 functions
 * See <a href="https://proandroiddev.com/kotlins-nothing-type-946de7d464fb">Credits to Alex Hart.</a>
 */
fun <A, B, C> ((A) -> B).c(f: (B) -> C): (A) -> C = {
    f(this(it))
}

/**
 * Right-biased flatMap() FP convention which means that Right is assumed to be the default case
 * to operate on. If it is Left, operations like map, flatMap, ... return the Left value unchanged.
 */
fun <T, L, R> ResultWrapper<L, R>.flatMap(fn: (R) -> ResultWrapper<L, T>): ResultWrapper<L, T> =
    when (this) {
        is Failure -> Failure(a)
        is Success -> fn(b)
    }

/**
 * Right-biased map() FP convention which means that Right is assumed to be the default case
 * to operate on. If it is Left, operations like map, flatMap, ... return the Left value unchanged.
 */
fun <T, L, R> ResultWrapper<L, R>.map(fn: (R) -> (T)): ResultWrapper<L, T> = this.flatMap(fn.c(::right))

/** Returns the value from this `Right` or the given argument if this is a `Left`.
 *  Right(12).getOrElse(17) RETURNS 12 and Left(12).getOrElse(17) RETURNS 17
 */
fun <L, R> ResultWrapper<L, R>.getOrElse(value: R): R =
    when (this) {
        is Failure -> value
        is Success -> b
    }

/**
 * Left-biased onFailure() FP convention dictates that when this class is Left, it'll perform
 * the onFailure functionality passed as a parameter, but, overall will still return an either
 * object so you chain calls.
 */
fun <L, R> ResultWrapper<L, R>.onFailure(fn: (failure: L) -> Unit): ResultWrapper<L, R> =
    this.apply { if (this is Failure) fn(a) }

/**
 * Right-biased onSuccess() FP convention dictates that when this class is Right, it'll perform
 * the onSuccess functionality passed as a parameter, but, overall will still return an either
 * object so you chain calls.
 */
fun <L, R> ResultWrapper<L, R>.onSuccess(fn: (success: R) -> Unit): ResultWrapper<L, R> =
    this.apply { if (this is Success) fn(b) }
