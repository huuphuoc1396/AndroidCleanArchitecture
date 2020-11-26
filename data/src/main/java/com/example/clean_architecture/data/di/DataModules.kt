package com.example.clean_architecture.data.di

import com.example.clean_architecture.data.local.di.localModule
import com.example.clean_architecture.data.remote.di.remoteModule
import com.example.clean_architecture.data.repository.di.repositoryModule
import org.koin.core.module.Module

val dataModules: List<Module> = listOf(
    localModule,
    remoteModule,
    repositoryModule
)