package com.example.clean_architecture.presentation.di

import com.example.clean_architecture.presentation.feature.detail.DetailViewModel
import com.example.clean_architecture.presentation.feature.main.MainViewModel
import com.example.clean_architecture.presentation.mapper.OwnerItemMapper
import com.example.clean_architecture.presentation.mapper.RepoItemMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        MainViewModel(
            searchReposUseCase = get(),
            repoItemMapper = get()
        )
    }

    viewModel {
        DetailViewModel()
    }

    factory {
        RepoItemMapper(
            ownerItemMapper = get()
        )
    }

    factory {
        OwnerItemMapper()
    }
}