package com.example.data.remote.di

import com.example.data.remote.api.RepoApi
import com.example.data.remote.interceptor.HeaderInterceptor
import com.example.data.remote.mapper.OwnerMapper
import com.example.data.remote.mapper.RepoMapper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val remoteModule = module {

    single {
        createRetrofit(
            headerInterceptor = get(),
            loggingInterceptor = get()
        ).create(RepoApi::class.java)
    }

    single {
        HeaderInterceptor()
    }

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
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

fun createRetrofit(
    headerInterceptor: HeaderInterceptor,
    loggingInterceptor: HttpLoggingInterceptor
): Retrofit {
    val client = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()
    return Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}