package com.example.data.remote.di

import com.example.data.remote.api.RepoApi
import com.example.data.remote.mapper.OwnerMapper
import com.example.data.remote.mapper.RepoMapper
import com.example.data.repository.RepoRepositoryImpl
import com.example.domain.repository.RepoRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val remoteModule = module {

    single {
        createRetrofit().create(RepoApi::class.java)
    }

    factory {
        RepoMapper(
            ownerMapper = get()
        )
    }

    factory {
        OwnerMapper()
    }
}

fun createRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}