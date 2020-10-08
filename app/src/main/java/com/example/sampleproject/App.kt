package com.example.sampleproject

import android.app.Application
import com.example.data.di.dataModule
import com.example.sampleproject.di.appModule
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
                dataModule,
                domainModule
            )
        }
    }
}