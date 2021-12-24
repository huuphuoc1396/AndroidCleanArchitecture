package com.example.clean_architecture.domain.repository

import kotlinx.coroutines.flow.Flow

interface AppRepository {

    fun isFirstRun(): Flow<Boolean>

    suspend fun setFirstRun()
}