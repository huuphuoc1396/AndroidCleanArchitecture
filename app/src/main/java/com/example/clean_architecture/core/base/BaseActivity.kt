package com.example.clean_architecture.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.tag(LIFECYCLE_TAG).i("${this::class.simpleName} onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onRestart() {
        Timber.tag(LIFECYCLE_TAG).i("${this::class.simpleName} onRestart")
        super.onRestart()
    }

    override fun onStart() {
        Timber.tag(LIFECYCLE_TAG).i("${this::class.simpleName} onStart")
        super.onStart()
    }

    override fun onResume() {
        Timber.tag(LIFECYCLE_TAG).i("${this::class.simpleName} onResume")
        super.onResume()
    }

    override fun onPause() {
        Timber.tag(LIFECYCLE_TAG).i("${this::class.simpleName} onPause")
        super.onPause()
    }

    override fun onStop() {
        Timber.tag(LIFECYCLE_TAG).i("${this::class.simpleName} onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Timber.tag(LIFECYCLE_TAG).i("${this::class.simpleName} onDestroy")
        super.onDestroy()
    }

    companion object {
        private const val LIFECYCLE_TAG = "ActivityLifecycle"
    }
}