package com.example.clean_architecture.presentation.feature.detail

import androidx.lifecycle.MutableLiveData
import com.example.clean_architecture.common.base.BaseViewModel

class DetailViewModel : BaseViewModel() {

    val detailModel = MutableLiveData<DetailModel>(initDetailModel())

    fun setDetailModel(
        repoName: String,
        ownerLogin: String
    ) {
        detailModel.value = detailModel.value?.copy(
            repoName = repoName,
            ownerLogin = ownerLogin
        )
    }

    private fun initDetailModel(): DetailModel {
        return DetailModel(
            repoName = "",
            ownerLogin = ""
        )
    }

    data class DetailModel(
        val repoName: String,
        val ownerLogin: String
    )
}