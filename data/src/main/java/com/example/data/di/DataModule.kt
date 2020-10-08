package com.example.data.di

import com.example.data.api.RepoApi
import com.example.data.mapper.RepoMapper
import com.example.data.repository.RepoRepositoryImpl
import com.example.domain.repository.RepoRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single<RepoRepository> {
        RepoRepositoryImpl(
            repoApi = get(),
            repoMapper = get()
        )
    }

    single<RepoApi> {
        createRetrofit().create(RepoApi::class.java)
    }

    factory {
        RepoMapper()
    }
}

fun createRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}