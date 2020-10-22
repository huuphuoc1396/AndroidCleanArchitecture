package com.example.domain.repository

import com.example.common_lib.result.ResultWrapper
import com.example.domain.model.Repo

interface RepoRepository {

    suspend fun searchRepos(query: String): ResultWrapper<List<Repo>>
}