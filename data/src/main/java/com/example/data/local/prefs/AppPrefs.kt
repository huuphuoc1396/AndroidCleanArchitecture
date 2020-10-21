package com.example.data.local.prefs

import android.content.Context
import android.content.SharedPreferences

class AppPrefs(context: Context) : PrefsHelper {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        context.packageName,
        Context.MODE_PRIVATE
    )

    override fun setFirstRun() {
        if (isFirstRun()) {
            sharedPreferences.edit().putBoolean(FIRST_RUN, false).apply()
        }
    }

    override fun isFirstRun(): Boolean {
        return sharedPreferences.getBoolean(FIRST_RUN, true)
    }

    companion object {

        private const val FIRST_RUN = "first_run"
    }
}