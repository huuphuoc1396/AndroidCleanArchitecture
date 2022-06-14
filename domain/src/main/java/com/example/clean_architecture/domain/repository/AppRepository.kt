package com.example.clean_architecture.domain.repository

import com.example.clean_architecture.domain.core.error.Failure
import com.example.clean_architecture.domain.core.functional.ResultWrapper

interface AppRepository {

    suspend fun isFirstRun(): ResultWrapper<Failure, Boolean>

    suspend fun setFirstRun()
}