package com.example.clean_architecture.di

import com.example.clean_architecture.data.di.dataModules
import com.example.clean_architecture.domain.di.domainModule
import com.example.clean_architecture.presentation.di.presentationModule
import org.koin.core.module.Module

val appModules: List<Module> = mutableListOf<Module>().apply {
    add(presentationModule)
    add(domainModule)
    addAll(dataModules)
}