package com.example.data.repository

import com.example.data.api.RepoApi
import com.example.data.mapper.RepoMapper
import com.example.domain.model.Repo
import com.example.domain.repository.RepoRepository
import com.example.lib.result.Result
import java.lang.Exception

class RepoRepositoryImpl(
    private val repoApi: RepoApi,
    private val repoMapper: RepoMapper
) : RepoRepository {

    override suspend fun searchRepos(query: String): Result<List<Repo>> {
        return try {
            val response = repoApi.searchRepos(query)
            val repos = repoMapper.mapList(response.items)
            Result.Success(repos)
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }

}