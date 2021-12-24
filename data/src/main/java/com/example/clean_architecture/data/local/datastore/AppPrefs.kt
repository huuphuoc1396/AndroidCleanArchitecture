package com.example.clean_architecture.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPrefs @Inject constructor(
    @ApplicationContext
    private val context: Context
) {

    val isFirstRun: Flow<Boolean> = get {
        it[KEY_FIRST_RUN] ?: true
    }

    suspend fun setFirstRun() = edit {
        it[KEY_FIRST_RUN] = false
    }

    private fun <T> get(transform: (Preferences) -> T): Flow<T> =
        context.appPrefs.data.map { transform(it) }

    private suspend fun edit(transform: suspend (MutablePreferences) -> Unit) {
        context.appPrefs.edit(transform)
    }

    companion object {
        private val KEY_FIRST_RUN = booleanPreferencesKey("key_first_run")
    }
}