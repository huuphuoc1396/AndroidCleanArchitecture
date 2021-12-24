package com.example.clean_architecture.domain.core.error

sealed class DataStoreFailure : Failure {

    object IOFailure : DataStoreFailure()

    object UnknownFailure : DataStoreFailure()
}