package com.example.clean_architecture.core.platform

import androidx.lifecycle.ViewModel
import com.example.clean_architecture.core.livedata.SingleLiveData
import com.example.clean_architecture.domain.core.error.CoroutineError

abstract class BaseViewModel : ViewModel() {

    val networkError = SingleLiveData<CoroutineError>()

    fun handleNetworkError(coroutineError: CoroutineError) {
        networkError.value = coroutineError
    }
}