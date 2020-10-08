package com.example.sampleproject.di

import com.example.sampleproject.mapper.RepoItemMapper
import com.example.sampleproject.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        MainViewModel(
            searchReposUseCase = get(),
            repoItemMapper = get()
        )
    }

    factory { RepoItemMapper() }
}