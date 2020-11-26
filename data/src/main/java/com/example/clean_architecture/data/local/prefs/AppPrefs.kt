package com.example.clean_architecture.data.local.prefs

import com.example.clean_architecture.data.local.prefs.api.SharedPrefsApi

class AppPrefs(
    private val sharedPrefsApi: SharedPrefsApi
) : PrefsHelper {

    override fun setFirstRun() {
        if (isFirstRun()) {
            sharedPrefsApi.set(FIRST_RUN, false)
        }
    }

    override fun isFirstRun(): Boolean {
        return sharedPrefsApi.get(FIRST_RUN, true)
    }

    companion object {
        private const val FIRST_RUN = "first_run"
    }
}