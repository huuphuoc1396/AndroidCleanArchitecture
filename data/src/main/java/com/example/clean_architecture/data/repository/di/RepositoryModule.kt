package com.example.clean_architecture.data.repository.di

import com.example.clean_architecture.data.repository.RepoRepositoryImpl
import com.example.clean_architecture.domain.repository.RepoRepository
import org.koin.dsl.module

internal val repositoryModule = module {

    single<RepoRepository> {
        RepoRepositoryImpl(
            repoApi = get(),
            repoMapper = get(),
            remoteCoroutineExceptionHandler = get()
        )
    }
}