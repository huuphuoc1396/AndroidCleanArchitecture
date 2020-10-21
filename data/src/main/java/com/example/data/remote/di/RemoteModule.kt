package com.example.data.remote.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.data.BuildConfig
import com.example.data.remote.api.RepoApi
import com.example.data.remote.exception.RemoteCoroutineExceptionHandler
import com.example.data.remote.interceptor.HeaderInterceptor
import com.example.data.remote.mapper.RepoMapper
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val DEFAULT_CONNECTION_TIMEOUT = 15000L

val remoteModule = module {

    single {
        get<Retrofit>().create(RepoApi::class.java)
    }

    single {
        createRetrofit(
            baseUrl = BuildConfig.BASE_URL,
            connectionTimeout = DEFAULT_CONNECTION_TIMEOUT,
            headerInterceptor = get(),
            chuckerInterceptor = get(),
            loggingInterceptor = get(),
            isLoggingEnable = BuildConfig.DEBUG
        )
    }

    factory {
        HeaderInterceptor()
    }

    factory {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    factory {
        ChuckerInterceptor(
            context = androidContext()
        )
    }

    factory {
        Gson()
    }

    factory {
        RemoteCoroutineExceptionHandler(
            gson = get()
        )
    }

    factory {
        RepoMapper()
    }
}

fun createRetrofit(
    baseUrl: String,
    connectionTimeout: Long,
    headerInterceptor: HeaderInterceptor,
    chuckerInterceptor: ChuckerInterceptor,
    loggingInterceptor: HttpLoggingInterceptor,
    isLoggingEnable: Boolean
): Retrofit {

    val builder = OkHttpClient.Builder()
        .connectTimeout(connectionTimeout, TimeUnit.MILLISECONDS)
        .addInterceptor(headerInterceptor)

    if (isLoggingEnable) {
        builder.addInterceptor(chuckerInterceptor)
            .addInterceptor(loggingInterceptor)
    }

    val client = builder.build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}