package com.example.clean_architecture.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.example.clean_architecture.data.local.datastore.AppPrefs.PreferencesKeys.KEY_FIRST_RUN
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPrefs @Inject constructor(
    @ApplicationContext
    private val context: Context
) {

    suspend fun isFirstRun(): Boolean = get {
        it[KEY_FIRST_RUN] ?: true
    }

    suspend fun setFirstRun() = edit {
        it[KEY_FIRST_RUN] = false
    }

    private suspend fun <T> get(transform: (Preferences) -> T): T =
        context.appPrefs.data.map { transform(it) }.first()

    private suspend fun edit(transform: suspend (MutablePreferences) -> Unit) {
        context.appPrefs.edit(transform)
    }

    private object PreferencesKeys {
        val KEY_FIRST_RUN = booleanPreferencesKey("key_first_run")
    }
}