package com.example.data.repository.di

import com.example.data.repository.RepoRepositoryImpl
import com.example.domain.repository.RepoRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<RepoRepository> {
        RepoRepositoryImpl(
            repoApi = get(),
            repoMapper = get(),
            remoteCoroutineExceptionHandler = get()
        )
    }
}