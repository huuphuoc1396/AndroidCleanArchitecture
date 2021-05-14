package com.example.clean_architecture.presentation.feature.detail

import androidx.lifecycle.MutableLiveData
import com.example.clean_architecture.core_android.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : BaseViewModel() {

    private var ownerLogin = ""

    val repoName = MutableLiveData("")

    fun init(
        repoName: String,
        ownerLogin: String,
    ) {
        this.repoName.value = repoName
        this.ownerLogin = ownerLogin
    }
}