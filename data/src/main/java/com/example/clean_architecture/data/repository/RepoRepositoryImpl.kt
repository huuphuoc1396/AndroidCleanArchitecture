package com.example.clean_architecture.data.repository

import com.example.clean_architecture.core_lib.result.ResultWrapper
import com.example.clean_architecture.data.remote.api.RepoApi
import com.example.clean_architecture.data.remote.exception.RemoteCoroutineExceptionHandler
import com.example.clean_architecture.data.remote.mapper.RepoMapper
import com.example.clean_architecture.domain.model.Repo
import com.example.clean_architecture.domain.repository.RepoRepository

class RepoRepositoryImpl(
    private val repoApi: RepoApi,
    private val repoMapper: RepoMapper,
    private val remoteCoroutineExceptionHandler: RemoteCoroutineExceptionHandler
) : RepoRepository {

    override suspend fun searchRepos(query: String): ResultWrapper<List<Repo>> {
        return ResultWrapper.safeSuspend(remoteCoroutineExceptionHandler) {
            val response = repoApi.searchRepos(query)
            val repos = repoMapper.mapList(response.items)
            ResultWrapper.Success(repos)
        }
    }

}