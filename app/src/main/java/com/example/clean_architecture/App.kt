package com.example.clean_architecture

import android.app.Application
import com.example.clean_architecture.presentation.di.presentationModule
import com.example.clean_architecture.presentation.di.viewModelModule
import com.example.data.local.di.localModule
import com.example.data.remote.di.remoteModule
import com.example.data.repository.di.repositoryModule
import com.example.domain.di.domainModule
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