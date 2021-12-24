package com.example.clean_architecture.data.local.datastore.error

import com.example.clean_architecture.domain.core.error.DataStoreFailure
import com.example.clean_architecture.domain.core.error.FailureHandler
import java.io.IOException
import javax.inject.Inject

class DataStoreFailureHandler @Inject constructor() : FailureHandler {
    override fun handleThrowable(throwable: Throwable) = when (throwable) {
        is IOException -> DataStoreFailure.IOFailure
        else -> DataStoreFailure.UnknownFailure
    }
}