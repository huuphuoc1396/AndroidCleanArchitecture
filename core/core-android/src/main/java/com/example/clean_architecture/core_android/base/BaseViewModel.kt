package com.example.clean_architecture.core_android.base

import androidx.lifecycle.ViewModel
import com.example.clean_architecture.core_android.livedata.SingleLiveData
import com.example.clean_architecture.core_lib.error.CoroutineError

abstract class BaseViewModel : ViewModel() {

    val networkError = SingleLiveData<CoroutineError>()

    fun handleNetworkError(coroutineError: CoroutineError) {
        networkError.value = coroutineError
    }
}