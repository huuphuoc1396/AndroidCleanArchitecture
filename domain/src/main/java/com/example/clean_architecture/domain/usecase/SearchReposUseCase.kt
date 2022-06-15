package com.example.clean_architecture.domain.usecase

import com.example.clean_architecture.domain.core.error.Failure
import com.example.clean_architecture.domain.core.functional.ResultWrapper
import com.example.clean_architecture.domain.core.interactor.params.UseCaseParams
import com.example.clean_architecture.domain.core.interactor.usecase.CoroutineUseCase
import com.example.clean_architecture.domain.model.RepoModel
import com.example.clean_architecture.domain.repository.RepoRepository
import javax.inject.Inject

class SearchReposUseCase @Inject constructor(
    private val repoRepository: RepoRepository,
) : CoroutineUseCase<SearchReposUseCase.Params, ResultWrapper<Failure, List<RepoModel>>>() {

    override suspend fun execute(params: Params): ResultWrapper<Failure, List<RepoModel>> {
        return repoRepository.searchRepos(params.query)
    }

    data class Params(
        val query: String,
    ) : UseCaseParams
}