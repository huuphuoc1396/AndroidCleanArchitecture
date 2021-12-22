package com.example.clean_architecture.domain.usecase

import com.example.clean_architecture.domain.core.result.ResultWrapper
import com.example.clean_architecture.domain.core.usecase.UseCase
import com.example.clean_architecture.domain.model.Repo
import com.example.clean_architecture.domain.repository.RepoRepository
import javax.inject.Inject

class SearchReposUseCase @Inject constructor(
    private val repoRepository: RepoRepository,
) : UseCase<SearchReposUseCase.Params, ResultWrapper<List<Repo>>>() {

    override suspend fun executeInternal(params: Params): ResultWrapper<List<Repo>> {
        return repoRepository.searchRepos(params.query)
    }

    data class Params(
        val query: String,
    ) : UseCase.Params()
}