package com.example.clean_architecture.domain.usecase

import com.example.clean_architecture.domain.core.interactor.params.EmptyParams
import com.example.clean_architecture.domain.core.interactor.usecase.CoroutineUseCase
import com.example.clean_architecture.domain.repository.AppRepository
import javax.inject.Inject

class SetFirstRunUseCase @Inject constructor(
    private val appRepository: AppRepository
) : CoroutineUseCase<EmptyParams, Unit>() {
    override suspend fun execute(params: EmptyParams) {
        appRepository.setFirstRun()
    }
}