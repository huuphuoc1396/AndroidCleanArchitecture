package com.example.clean_architecture.domain.di

import com.example.clean_architecture.domain.usecase.SearchReposUseCase
import org.koin.dsl.module

val domainModule = module {

    factory {
        SearchReposUseCase(
            repoRepository = get()
        )
    }
}