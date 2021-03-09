package com.example.clean_architecture.data.local.prefs

import android.content.Context
import android.content.SharedPreferences
import androidx.test.platform.app.InstrumentationRegistry
import com.example.clean_architecture.data.local.prefs.api.SharedPrefsApi
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AppPrefsTest {

    private val context: Context = InstrumentationRegistry.getInstrumentation().context

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    private val sharedPrefsApi = SharedPrefsApi(
        sharedPreferences = sharedPreferences
    )

    private val appPrefs = AppPrefs(
        sharedPrefsApi = sharedPrefsApi
    )

    @Test
    fun isFirstRun() {
        assertEquals(appPrefs.isFirstRun(), true)
    }

    @Test
    fun setFirstRun() {
        appPrefs.setFirstRun()
        assertEquals(appPrefs.isFirstRun(), false)

        // setFirstRun() again
        appPrefs.setFirstRun()
        assertEquals(appPrefs.isFirstRun(), false)
    }
}