package com.example.common_android.base

import androidx.lifecycle.ViewModel
import com.example.common_android.livedata.SingleLiveData
import com.example.common_lib.exception.CoroutineException

abstract class BaseViewModel : ViewModel() {

    val networkError = SingleLiveData<CoroutineException>()

    fun handleNetworkError(coroutineException: CoroutineException) {
        networkError.value = coroutineException
    }
}