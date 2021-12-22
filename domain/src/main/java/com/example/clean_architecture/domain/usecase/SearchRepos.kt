package com.example.clean_architecture.domain.usecase

import com.example.clean_architecture.domain.core.error.CoroutineError
import com.example.clean_architecture.domain.core.functional.Either
import com.example.clean_architecture.domain.core.interactor.UseCase
import com.example.clean_architecture.domain.core.interactor.UseCaseParams
import com.example.clean_architecture.domain.model.Repo
import com.example.clean_architecture.domain.repository.RepoRepository
import javax.inject.Inject

class SearchRepos @Inject constructor(
    private val repoRepository: RepoRepository,
) : UseCase<SearchRepos.Params, Either<CoroutineError, List<Repo>>>() {

    override suspend fun executeInternal(params: Params): Either<CoroutineError, List<Repo>> {
        return repoRepository.searchRepos(params.query)
    }

    data class Params(
        val query: String,
    ) : UseCaseParams()
}