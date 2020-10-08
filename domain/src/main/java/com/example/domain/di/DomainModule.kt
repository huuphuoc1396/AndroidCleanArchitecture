package com.example.domain.di

import com.example.domain.usecase.SearchReposUseCase
import org.koin.dsl.module

val domainModule = module {

    factory {
        SearchReposUseCase(
            repoRepository = get()
        )
    }
}