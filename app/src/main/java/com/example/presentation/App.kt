package com.example.presentation

import android.app.Application
import com.example.data.remote.di.remoteModule
import com.example.data.repository.di.repositoryModule
import com.example.presentation.di.appModule
import com.example.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                appModule,
                remoteModule,
                repositoryModule,
                domainModule
            )
        }
    }
}