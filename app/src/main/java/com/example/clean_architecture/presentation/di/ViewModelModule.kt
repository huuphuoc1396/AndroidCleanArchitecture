package com.example.clean_architecture.presentation.di

import com.example.clean_architecture.presentation.feature.detail.DetailViewModel
import com.example.clean_architecture.presentation.feature.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MainViewModel(
            searchReposUseCase = get(),
            repoItemMapper = get()
        )
    }

    viewModel {
        DetailViewModel()
    }
}