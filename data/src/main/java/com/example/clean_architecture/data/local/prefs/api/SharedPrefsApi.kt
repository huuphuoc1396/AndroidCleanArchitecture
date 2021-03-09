package com.example.clean_architecture.data.local.prefs.api

import android.content.SharedPreferences
import android.net.Uri
import android.util.LongSparseArray
import com.google.gson.Gson

class SharedPrefsApi(
    val sharedPreferences: SharedPreferences
) {
    fun set(key: String, value: String) = sharedPreferences.edit().putString(key, value).apply()

    fun get(key: String, defValue: String) = sharedPreferences.getString(key, defValue)

    fun set(key: String, value: Int) = sharedPreferences.edit().putInt(key, value).apply()

    fun get(key: String, defValue: Int) = sharedPreferences.getInt(key, defValue)

    fun set(key: String, value: Boolean) = sharedPreferences.edit().putBoolean(key, value).apply()

    fun get(key: String, defValue: Boolean) = sharedPreferences.getBoolean(key, defValue)

    fun set(key: String, value: Long) = sharedPreferences.edit().putLong(key, value).apply()

    fun get(key: String, defValue: Long) = sharedPreferences.getLong(key, defValue)

    fun clear() = sharedPreferences.edit().clear().apply()

    fun remove(key: String) = sharedPreferences.edit().remove(key).apply()

    fun <T> setList(key: String, list: List<T>) {
        val json = Gson().toJson(list)
        set(key, json)
    }

    fun setLongSparseArray(key: String, array: LongSparseArray<Boolean>) {
        val json = Gson().toJson(array)
        set(key, json)
    }

    fun contains(key: String) = sharedPreferences.contains(key)

    inline fun <reified T> setObject(key: String, value: T) = sharedPreferences.edit().apply {
        val json: String = Gson().newBuilder()
            .registerTypeAdapter(Uri::class.java, UriSerializer())
            .create()
            .toJson(value)
        putString(key, json)
    }.apply()

    inline fun <reified T> getObject(key: String): T? = run {
        val data = get(key, "")
        return if (data.isNullOrEmpty()) {
            null
        } else {
            Gson().newBuilder()
                .registerTypeAdapter(Uri::class.java, UriDeserializer())
                .create()
                .fromJson(data, T::class.java)
        }
    }
}