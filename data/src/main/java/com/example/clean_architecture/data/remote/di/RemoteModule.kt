package com.example.clean_architecture.data.remote.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.clean_architecture.data.remote.api.RepoApi
import com.example.clean_architecture.data.remote.interceptor.HeaderInterceptor
import com.example.data.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    @Singleton
    fun provideRepoApi(
        @ApplicationContext context: Context,
        headerInterceptor: HeaderInterceptor,
    ): RepoApi {
        return createRetrofit(
            context = context,
            headerInterceptor = headerInterceptor
        ).create(RepoApi::class.java)
    }

    private fun createRetrofit(context: Context, headerInterceptor: HeaderInterceptor, ): Retrofit {
        val okHttpClient = OkHttpClient.Builder().apply {
            connectTimeout(DEFAULT_CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
            addInterceptor(headerInterceptor)
            if (BuildConfig.DEBUG) {
                val chuckerInterceptor: ChuckerInterceptor = ChuckerInterceptor.Builder(
                    context = context,
                ).build()
                val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
                addInterceptor(chuckerInterceptor)
                addInterceptor(loggingInterceptor)
            }
        }.build()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        private const val DEFAULT_CONNECTION_TIMEOUT = 15000L
    }

}