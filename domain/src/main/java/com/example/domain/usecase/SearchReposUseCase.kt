package com.example.domain.usecase

import com.example.domain.model.Repo
import com.example.domain.repository.RepoRepository
import com.example.lib.result.Result
import com.example.lib.usecase.UseCase

class SearchReposUseCase(
    private val repoRepository: RepoRepository
) : UseCase<SearchReposUseCase.Params, Result<List<Repo>>>() {

    override suspend fun execute(params: Params): Result<List<Repo>> {
        return repoRepository.searchRepos(params.query)
    }

    data class Params(
        val query: String
    ) : UseCase.Params()
}