package com.example.lib.result

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

    val isSuccess get() = this is Success
    val isError get() = this is Error

    fun subscribe(success: (R) -> Unit, error: (Exception) -> Unit) {
        when (this) {
            is Success -> success(this.data)
            is Error -> error(this.exception)
        }
    }
}