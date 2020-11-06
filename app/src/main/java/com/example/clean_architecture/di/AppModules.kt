package com.example.clean_architecture.di

import com.example.clean_architecture.presentation.di.presentationModule
import com.example.data.di.dataModules
import com.example.domain.di.domainModule
import org.koin.core.module.Module

val appModules: List<Module> = mutableListOf<Module>().apply {
    add(presentationModule)
    add(domainModule)
    addAll(dataModules)
}