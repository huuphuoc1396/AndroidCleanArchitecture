package com.example.presentation.di

import com.example.presentation.feature.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MainViewModel(
            searchReposUseCase = get(),
            repoItemMapper = get()
        )
    }
}