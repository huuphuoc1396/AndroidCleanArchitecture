package com.example.clean_architecture.core.platform

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clean_architecture.core.livedata.SingleLiveData
import com.example.clean_architecture.domain.core.error.Failure

abstract class BaseViewModel : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    val failure = SingleLiveData<Failure>()

    fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }

    fun setLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }
}