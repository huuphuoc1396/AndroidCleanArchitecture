package com.example.clean_architecture.data.local.prefs

interface PrefsHelper {

    fun setFirstRun()

    fun isFirstRun(): Boolean
}