package com.example.clean_architecture.data.repository

import com.example.clean_architecture.data.local.datastore.AppPrefs
import com.example.clean_architecture.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val appPrefs: AppPrefs
) : AppRepository {

    override fun isFirstRun(): Flow<Boolean> {
        return appPrefs.isFirstRun
    }

    override suspend fun setFirstRun() {
        appPrefs.setFirstRun()
    }
}