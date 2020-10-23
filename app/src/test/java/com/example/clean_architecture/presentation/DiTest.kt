package com.example.clean_architecture.presentation

import androidx.test.platform.app.InstrumentationRegistry
import com.example.clean_architecture.presentation.di.presentationModule
import com.example.clean_architecture.presentation.di.viewModelModule
import com.example.data.local.di.localModule
import com.example.data.remote.di.remoteModule
import com.example.data.repository.di.repositoryModule
import com.example.domain.di.domainModule
import org.junit.Test
import org.junit.experimental.categories.Category
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.test.AutoCloseKoinTest
import org.koin.test.category.CheckModuleTest
import org.koin.test.check.checkModules
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
@Category(CheckModuleTest::class)
class DiTest : AutoCloseKoinTest() {

    @Test
    fun checkModules() = checkModules {
        val context = InstrumentationRegistry.getInstrumentation().context
        androidContext(context)
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