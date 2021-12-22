package com.example.clean_architecture.domain.repository

import com.example.clean_architecture.domain.core.error.CoroutineError
import com.example.clean_architecture.domain.core.functional.Either
import com.example.clean_architecture.domain.model.Repo

interface RepoRepository {

    suspend fun searchRepos(query: String): Either<CoroutineError, List<Repo>>
}