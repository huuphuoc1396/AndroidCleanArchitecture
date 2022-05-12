package com.example.clean_architecture.domain.usecase

import com.example.clean_architecture.domain.core.error.Failure
import com.example.clean_architecture.domain.core.functional.ResultWrapper
import com.example.clean_architecture.domain.core.interactor.params.EmptyParams
import com.example.clean_architecture.domain.core.interactor.usecase.BasicUseCase
import com.example.clean_architecture.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsFirstRun @Inject constructor(
    private val appRepository: AppRepository,
) : BasicUseCase<EmptyParams, Flow<ResultWrapper<Failure, Boolean>>>() {

    override fun execute(params: EmptyParams): Flow<ResultWrapper<Failure, Boolean>> {
        return appRepository.isFirstRun()
    }
}