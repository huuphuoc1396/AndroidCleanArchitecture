package com.example.clean_architecture.data.repository.di

import com.example.clean_architecture.data.repository.AppRepositoryImpl
import com.example.clean_architecture.data.repository.RepoRepositoryImpl
import com.example.clean_architecture.domain.repository.AppRepository
import com.example.clean_architecture.domain.repository.RepoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideRepoRepository(repositoryImpl: RepoRepositoryImpl): RepoRepository {
        return repositoryImpl
    }


    @Provides
    fun provideAppRepository(repositoryImpl: AppRepositoryImpl): AppRepository {
        return repositoryImpl
    }
}