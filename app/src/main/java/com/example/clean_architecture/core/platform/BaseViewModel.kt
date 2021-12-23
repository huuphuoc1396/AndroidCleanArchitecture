package com.example.clean_architecture.core.platform

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clean_architecture.core.livedata.SingleLiveData
import com.example.clean_architecture.domain.core.error.CoroutineError

abstract class BaseViewModel : ViewModel() {
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    val networkError = SingleLiveData<CoroutineError>()

    fun handleNetworkError(coroutineError: CoroutineError) {
        networkError.value = coroutineError
    }

    fun setLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }
}