package com.example.clean_architecture.core_android.base

import androidx.lifecycle.ViewModel
import com.example.clean_architecture.core_android.livedata.SingleLiveData
import com.example.clean_architecture.core_lib.exception.CoroutineException

abstract class BaseViewModel : ViewModel() {

    val networkError = SingleLiveData<CoroutineException>()

    fun handleNetworkError(coroutineException: CoroutineException) {
        networkError.value = coroutineException
    }
}