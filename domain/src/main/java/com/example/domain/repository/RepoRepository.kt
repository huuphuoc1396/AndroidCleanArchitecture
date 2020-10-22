package com.example.domain.repository

import com.example.domain.model.Repo
import com.example.lib.result.ResultWrapper

interface RepoRepository {

    suspend fun searchRepos(query: String): ResultWrapper<List<Repo>>
}