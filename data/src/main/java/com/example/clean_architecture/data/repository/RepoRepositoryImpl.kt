package com.example.clean_architecture.data.repository

import com.example.clean_architecture.data.remote.error.RemoteFailureHandler
import com.example.clean_architecture.data.remote.mapper.RepoMapper
import com.example.clean_architecture.data.remote.retrofit.api.RepoApi
import com.example.clean_architecture.domain.core.error.Failure
import com.example.clean_architecture.domain.core.functional.Either
import com.example.clean_architecture.domain.core.functional.safeSuspend
import com.example.clean_architecture.domain.model.Repo
import com.example.clean_architecture.domain.repository.RepoRepository
import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(
    private val repoApi: RepoApi,
    private val repoMapper: RepoMapper,
    private val remoteCoroutineErrorHandler: RemoteFailureHandler,
) : RepoRepository {

    override suspend fun searchRepos(query: String): Either<Failure, List<Repo>> {
        return safeSuspend(remoteCoroutineErrorHandler) {
            val response = repoApi.searchRepos(query)
            val repos = repoMapper.mapList(response.items)
            Either.Right(repos)
        }
    }

}