package com.example.presentation.di

import com.example.presentation.mapper.RepoItemMapper
import com.example.presentation.MainViewModel
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