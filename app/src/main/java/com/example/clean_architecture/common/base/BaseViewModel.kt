package com.example.clean_architecture.common.base

import androidx.lifecycle.ViewModel
import com.example.clean_architecture.common.livedata.SingleLiveData
import com.example.common_lib.exception.CoroutineException

abstract class BaseViewModel : ViewModel() {

    val networkError = SingleLiveData<CoroutineException>()

    fun handleNetworkError(coroutineException: CoroutineException) {
        networkError.value = coroutineException
    }
}