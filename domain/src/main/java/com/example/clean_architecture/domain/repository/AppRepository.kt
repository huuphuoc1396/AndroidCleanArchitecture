package com.example.clean_architecture.domain.repository

import com.example.clean_architecture.domain.core.error.Failure
import com.example.clean_architecture.domain.core.functional.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    fun isFirstRun(): Flow<ResultWrapper<Failure, Boolean>>

    suspend fun setFirstRun()
}