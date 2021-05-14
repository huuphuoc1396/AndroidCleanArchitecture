package com.example.clean_architecture.data.repository.di

import com.example.clean_architecture.data.repository.RepoRepositoryImpl
import com.example.clean_architecture.domain.repository.RepoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideRepoRepository(repoRepositoryImpl: RepoRepositoryImpl): RepoRepository {
        return repoRepositoryImpl
    }
}