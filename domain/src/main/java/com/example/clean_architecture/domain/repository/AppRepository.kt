package com.example.clean_architecture.domain.repository

import com.example.clean_architecture.domain.core.error.Failure
import com.example.clean_architecture.domain.core.functional.Either
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    fun isFirstRun(): Flow<Either<Failure, Boolean>>

    suspend fun setFirstRun()
}