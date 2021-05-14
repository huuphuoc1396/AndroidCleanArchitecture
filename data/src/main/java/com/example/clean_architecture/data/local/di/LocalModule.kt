package com.example.clean_architecture.data.local.di

import android.content.Context
import android.content.SharedPreferences
import com.example.clean_architecture.data.local.prefs.AppPrefs
import com.example.clean_architecture.data.local.prefs.PrefsHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Provides
    fun providePrefsHelper(appPrefs: AppPrefs): PrefsHelper {
        return appPrefs
    }

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }
}