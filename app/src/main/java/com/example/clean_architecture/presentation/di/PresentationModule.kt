package com.example.clean_architecture.presentation.di

import com.example.clean_architecture.presentation.mapper.RepoItemMapper
import org.koin.dsl.module

val presentationModule = module {

    factory { RepoItemMapper() }
}