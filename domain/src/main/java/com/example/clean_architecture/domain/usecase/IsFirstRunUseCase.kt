package com.example.clean_architecture.domain.usecase

import com.example.clean_architecture.domain.core.error.Failure
import com.example.clean_architecture.domain.core.functional.ResultWrapper
import com.example.clean_architecture.domain.core.interactor.params.EmptyParams
import com.example.clean_architecture.domain.core.interactor.usecase.CoroutineUseCase
import com.example.clean_architecture.domain.repository.AppRepository
import javax.inject.Inject

class IsFirstRunUseCase @Inject constructor(
    private val appRepository: AppRepository,
) : CoroutineUseCase<EmptyParams, ResultWrapper<Failure, Boolean>>() {

    override suspend fun execute(params: EmptyParams): ResultWrapper<Failure, Boolean> {
        return appRepository.isFirstRun()
    }
}