package com.example.clean_architecture.data.remote.di

import android.content.Context
import com.example.clean_architecture.data.remote.retrofit.builder.DefaultRetrofitBuilder
import com.example.clean_architecture.data.remote.retrofit.api.RepoApi
import com.example.clean_architecture.data.remote.retrofit.interceptor.HeaderInterceptor
import com.example.data.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    @Singleton
    fun provideRepoApi(
        @ApplicationContext
        context: Context,
        headerInterceptor: HeaderInterceptor,
        defaultRetrofitBuilder: DefaultRetrofitBuilder,
    ): RepoApi {
        return defaultRetrofitBuilder.baseUrl(BuildConfig.BASE_URL)
            .enableLogging(BuildConfig.DEBUG)
            .enableChucker(context, BuildConfig.DEBUG)
            .addInterceptor(headerInterceptor)
            .build()
            .create(RepoApi::class.java)
    }
}