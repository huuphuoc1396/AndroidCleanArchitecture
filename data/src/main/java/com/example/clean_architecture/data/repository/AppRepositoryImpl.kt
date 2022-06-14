package com.example.clean_architecture.data.repository

import com.example.clean_architecture.data.local.datastore.AppPrefs
import com.example.clean_architecture.data.local.datastore.error.DataStoreFailureHandler
import com.example.clean_architecture.domain.core.error.Failure
import com.example.clean_architecture.domain.core.functional.ResultWrapper
import com.example.clean_architecture.domain.core.functional.safeSuspend
import com.example.clean_architecture.domain.core.functional.safeSuspendIgnoreFailure
import com.example.clean_architecture.domain.repository.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val appPrefs: AppPrefs,
    private val dataStoreFailureHandler: DataStoreFailureHandler,
) : AppRepository {

    override suspend fun isFirstRun(): ResultWrapper<Failure, Boolean> {
        return safeSuspend(dataStoreFailureHandler) {
            val isFirstRun = appPrefs.isFirstRun()
            ResultWrapper.Success(isFirstRun)
        }
    }

    override suspend fun setFirstRun() = safeSuspendIgnoreFailure {
        appPrefs.setFirstRun()
    }
}