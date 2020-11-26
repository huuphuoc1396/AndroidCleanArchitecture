package com.example.clean_architecture.data.remote.response

import com.google.gson.annotations.SerializedName

data class RepoListResponse(
    @SerializedName("total_count")
    val totalCount: Int?,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean?,
    @SerializedName("items")
    val items: List<ItemResponse?>?
)