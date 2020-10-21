package com.example.common.di

import com.example.common.viewmodel.NavigationSharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val commonModule = module {

    viewModel {
        NavigationSharedViewModel()
    }
}