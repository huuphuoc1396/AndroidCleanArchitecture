package com.example.presentation

import android.app.Application
import com.example.data.local.di.localModule
import com.example.data.remote.di.remoteModule
import com.example.data.repository.di.repositoryModule
import com.example.domain.di.domainModule
import com.example.presentation.di.presentationModule
import com.example.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidContext(this@App)
            modules(
                presentationModule,
                viewModelModule,
                localModule,
                remoteModule,
                repositoryModule,
                domainModule
            )
        }
    }
}