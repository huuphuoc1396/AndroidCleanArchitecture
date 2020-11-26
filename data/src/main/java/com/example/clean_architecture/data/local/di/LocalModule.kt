package com.example.clean_architecture.data.local.di

import android.content.Context
import android.content.SharedPreferences
import com.example.clean_architecture.data.local.prefs.AppPrefs
import com.example.clean_architecture.data.local.prefs.PrefsHelper
import com.example.clean_architecture.data.local.prefs.api.SharedPrefsApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

internal val localModule = module {

    single<PrefsHelper> {
        AppPrefs(
            sharedPrefsApi = get()
        )
    }

    single {
        SharedPrefsApi(
            gson = get(),
            sharedPreferences = get()
        )
    }

    single<SharedPreferences> {
        val context: Context = androidContext()
        return@single context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }
}