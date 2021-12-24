package com.example.clean_architecture.domain.repository

import com.example.clean_architecture.domain.core.error.Failure
import com.example.clean_architecture.domain.core.functional.Either
import com.example.clean_architecture.domain.model.Repo

interface RepoRepository {

    suspend fun searchRepos(query: String): Either<Failure, List<Repo>>
}