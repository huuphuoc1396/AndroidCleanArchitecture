package com.example.clean_architecture.data.remote.retrofit.builder

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DefaultRetrofitBuilder @Inject constructor() {

    private var context: Context? = null

    private var baseUrl = ""

    private var readTimeout = DEFAULT_READ_TIMEOUT

    private var writeTimeout = DEFAULT_WRITE_TIMEOUT

    private var connectTimeout = DEFAULT_CONNECTION_TIMEOUT

    private var interceptors = mutableListOf<Interceptor>()

    private var isLoggingEnabled = false

    private var isChuckerEnabled = false

    fun baseUrl(baseUrl: String): DefaultRetrofitBuilder {
        this.baseUrl = baseUrl
        return this
    }

    fun readTimeout(connectionTimeout: Long): DefaultRetrofitBuilder {
        this.readTimeout = connectionTimeout
        return this
    }

    fun writeTimeout(connectionTimeout: Long): DefaultRetrofitBuilder {
        this.writeTimeout = connectionTimeout
        return this
    }

    fun connectTimeout(connectTimeout: Long): DefaultRetrofitBuilder {
        this.connectTimeout = connectTimeout
        return this
    }

    fun addInterceptor(interceptor: Interceptor): DefaultRetrofitBuilder {
        this.interceptors.add(interceptor)
        return this
    }

    fun enableLogging(isEnabled: Boolean): DefaultRetrofitBuilder {
        this.isLoggingEnabled = isEnabled
        return this
    }

    fun enableChucker(context: Context, isEnabled: Boolean): DefaultRetrofitBuilder {
        this.context = context
        this.isChuckerEnabled = isEnabled
        return this
    }

    fun build(): Retrofit {
        val okHttpClient = OkHttpClient.Builder().apply {
            readTimeout(readTimeout, TimeUnit.MILLISECONDS)
            writeTimeout(writeTimeout, TimeUnit.MILLISECONDS)
            connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)

            if (isLoggingEnabled) {
                val loggingInterceptor = HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
                addInterceptor(loggingInterceptor)
            }

            if (isChuckerEnabled) {
                context?.let { context ->
                    ChuckerInterceptor.Builder(context).build()
                }?.let { chuckerInterceptor ->
                    addInterceptor(chuckerInterceptor)
                }
            }

            // other interceptors
            interceptors.forEach { addInterceptor(it) }
        }.build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        private const val DEFAULT_READ_TIMEOUT = 15000L
        private const val DEFAULT_WRITE_TIMEOUT = 15000L
        private const val DEFAULT_CONNECTION_TIMEOUT = 15000L
    }
}