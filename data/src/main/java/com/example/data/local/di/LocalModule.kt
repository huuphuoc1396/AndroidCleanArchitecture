package com.example.data.local.di

import android.content.Context
import android.content.SharedPreferences
import com.example.data.local.common.SharedPrefsApi
import com.example.data.local.prefs.AppPrefs
import com.example.data.local.prefs.PrefsHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {

    single<PrefsHelper> {
        AppPrefs(
            sharedPrefsApi = get()
        )
    }

    single {
        SharedPrefsApi(
            sharedPreferences = get()
        )
    }

    single<SharedPreferences> {
        val context: Context = androidContext()
        return@single context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }
}