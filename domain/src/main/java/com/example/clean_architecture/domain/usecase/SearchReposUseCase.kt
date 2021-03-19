package com.example.clean_architecture.domain.usecase

import com.example.clean_architecture.core_lib.result.ResultWrapper
import com.example.clean_architecture.core_lib.usecase.UseCase
import com.example.clean_architecture.domain.model.Repo
import com.example.clean_architecture.domain.repository.RepoRepository

class SearchReposUseCase(
    private val repoRepository: RepoRepository,
) : UseCase<SearchReposUseCase.Params, ResultWrapper<List<Repo>>>() {

    override suspend fun executeInternal(params: Params): ResultWrapper<List<Repo>> {
        return repoRepository.searchRepos(params.query)
    }

    data class Params(
        val query: String,
    ) : UseCase.Params()
}