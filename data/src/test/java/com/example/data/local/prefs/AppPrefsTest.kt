package com.example.data.local.prefs

import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AppPrefsTest {

    private val appPrefs = AppPrefs(
        context = InstrumentationRegistry.getInstrumentation().context
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