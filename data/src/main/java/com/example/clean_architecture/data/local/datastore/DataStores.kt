package com.example.clean_architecture.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

private const val APP_PREFS_NAME = "app_data_store"
val Context.appPrefs: DataStore<Preferences> by preferencesDataStore(APP_PREFS_NAME)