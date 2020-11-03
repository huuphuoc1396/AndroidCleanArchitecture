package com.example.domain.usecase

import com.example.common_lib.result.ResultWrapper
import com.example.common_lib.usecase.UseCase
import com.example.domain.model.Repo
import com.example.domain.repository.RepoRepository

class SearchReposUseCase(
    private val repoRepository: RepoRepository
) : UseCase<SearchReposUseCase.Params, ResultWrapper<List<Repo>>>() {

    override suspend fun executeInternal(params: Params): ResultWrapper<List<Repo>> {
        return repoRepository.searchRepos(params.query)
    }

    data class Params(
        val query: String
    ) : UseCase.Params()
}