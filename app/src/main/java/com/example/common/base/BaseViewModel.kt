package com.example.common.base

import androidx.lifecycle.ViewModel
import com.example.common.livedata.SingleLiveData
import com.example.lib.exception.CoroutineException

abstract class BaseViewModel : ViewModel() {

    val networkError = SingleLiveData<CoroutineException>()

    fun handleNetworkError(coroutineException: CoroutineException) {
        networkError.value = coroutineException
    }
}