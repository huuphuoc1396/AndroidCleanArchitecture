package com.example.clean_architecture.domain.repository

import com.example.clean_architecture.domain.core.result.ResultWrapper
import com.example.clean_architecture.domain.model.Repo

interface RepoRepository {

    suspend fun searchRepos(query: String): ResultWrapper<List<Repo>>
}