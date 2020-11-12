package com.example.clean_architecture.presentation.feature.detail

import androidx.lifecycle.MutableLiveData
import com.example.common_android.base.BaseViewModel

class DetailViewModel : BaseViewModel() {

    val detailItem = MutableLiveData<DetailItem>(initDetailItem())

    fun setDetailItem(
        repoName: String,
        ownerLogin: String
    ) {
        detailItem.value = detailItem.value?.copy(
            repoName = repoName,
            ownerLogin = ownerLogin
        )
    }

    private fun initDetailItem(): DetailItem {
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