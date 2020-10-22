package com.example.data.repository

import com.example.common_lib.result.ResultWrapper
import com.example.data.remote.api.RepoApi
import com.example.data.remote.exception.RemoteCoroutineExceptionHandler
import com.example.data.remote.mapper.RepoMapper
import com.example.domain.model.Repo
import com.example.domain.repository.RepoRepository

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