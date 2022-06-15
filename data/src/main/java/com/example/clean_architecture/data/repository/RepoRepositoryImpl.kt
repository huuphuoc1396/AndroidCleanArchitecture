package com.example.clean_architecture.data.repository

import com.example.clean_architecture.data.remote.error.RemoteFailureHandler
import com.example.clean_architecture.data.remote.mapper.RepoModelMapper
import com.example.clean_architecture.data.remote.retrofit.api.RepoApi
import com.example.clean_architecture.domain.core.error.Failure
import com.example.clean_architecture.domain.core.functional.ResultWrapper
import com.example.clean_architecture.domain.core.functional.safeSuspend
import com.example.clean_architecture.domain.model.RepoModel
import com.example.clean_architecture.domain.repository.RepoRepository
import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(
    private val repoApi: RepoApi,
    private val repoModelMapper: RepoModelMapper,
    private val remoteCoroutineErrorHandler: RemoteFailureHandler,
) : RepoRepository {

    override suspend fun searchRepos(query: String): ResultWrapper<Failure, List<RepoModel>> {
        return safeSuspend(remoteCoroutineErrorHandler) {
            val response = repoApi.searchRepos(query)
            val repos = repoModelMapper.mapList(response.items)
            ResultWrapper.Success(repos)
        }
    }

}