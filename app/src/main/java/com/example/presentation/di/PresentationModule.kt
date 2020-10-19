package com.example.presentation.di

import com.example.presentation.mapper.RepoItemMapper
import org.koin.dsl.module

val presentationModule = module {
    factory { RepoItemMapper() }
}