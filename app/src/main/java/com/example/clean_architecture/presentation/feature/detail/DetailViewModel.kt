package com.example.clean_architecture.presentation.feature.detail

import androidx.lifecycle.MutableLiveData
import com.example.clean_architecture.common.base.BaseViewModel

class DetailViewModel : BaseViewModel() {

    val detailItem = MutableLiveData<DetailItem>(initDetailModel())

    fun setDetailModel(
        repoName: String,
        ownerLogin: String
    ) {
        detailItem.value = detailItem.value?.copy(
            repoName = repoName,
            ownerLogin = ownerLogin
        )
    }

    private fun initDetailModel(): DetailItem {
        return DetailItem(
            repoName = "",
            ownerLogin = ""
        )
    }

    data class DetailItem(
        val repoName: String,
        val ownerLogin: String
    )
}