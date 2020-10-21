package com.example.data.local.di

import com.example.data.local.prefs.AppPrefs
import com.example.data.local.prefs.PrefsHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {

    single<PrefsHelper> {
        AppPrefs(androidContext())
    }
}