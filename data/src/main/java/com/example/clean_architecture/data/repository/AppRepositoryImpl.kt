package com.example.clean_architecture.data.repository

import com.example.clean_architecture.data.local.datastore.AppPrefs
import com.example.clean_architecture.data.local.datastore.error.DataStoreFailureHandler
import com.example.clean_architecture.domain.core.error.Failure
import com.example.clean_architecture.domain.core.functional.Either
import com.example.clean_architecture.domain.core.functional.safeSuspendIgnoreFailure
import com.example.clean_architecture.domain.core.functional.toEither
import com.example.clean_architecture.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val appPrefs: AppPrefs,
    private val dataStoreFailureHandler: DataStoreFailureHandler,
) : AppRepository {

    override fun isFirstRun(): Flow<Either<Failure, Boolean>> {
        return appPrefs.isFirstRun.toEither(dataStoreFailureHandler)
    }

    override suspend fun setFirstRun() = safeSuspendIgnoreFailure {
        appPrefs.setFirstRun()
    }
}