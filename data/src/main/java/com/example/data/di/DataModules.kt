package com.example.data.di

import com.example.data.local.di.localModule
import com.example.data.remote.di.remoteModule
import com.example.data.repository.di.repositoryModule
import org.koin.core.module.Module

val dataModules: List<Module> = listOf(
    localModule,
    remoteModule,
    repositoryModule
)