package com.example.domain.repository

import com.example.domain.model.Repo
import com.example.lib.result.Result

interface RepoRepository {

    suspend fun searchRepos(query: String): Result<List<Repo>>
}