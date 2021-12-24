package com.example.clean_architecture.domain.usecase

import com.example.clean_architecture.domain.core.error.Failure
import com.example.clean_architecture.domain.core.functional.Either
import com.example.clean_architecture.domain.core.interactor.usecase.CoroutineUseCase
import com.example.clean_architecture.domain.core.interactor.params.UseCaseParams
import com.example.clean_architecture.domain.model.Repo
import com.example.clean_architecture.domain.repository.RepoRepository
import javax.inject.Inject

class SearchRepos @Inject constructor(
    private val repoRepository: RepoRepository,
) : CoroutineUseCase<SearchRepos.Params, Either<Failure, List<Repo>>>() {

    override suspend fun execute(params: Params): Either<Failure, List<Repo>> {
        return repoRepository.searchRepos(params.query)
    }

    data class Params(
        val query: String,
    ) : UseCaseParams
}