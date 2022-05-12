package com.example.clean_architecture.domain.repository

import com.example.clean_architecture.domain.core.error.Failure
import com.example.clean_architecture.domain.core.functional.ResultWrapper
import com.example.clean_architecture.domain.model.Repo

interface RepoRepository {

    suspend fun searchRepos(query: String): ResultWrapper<Failure, List<Repo>>
}