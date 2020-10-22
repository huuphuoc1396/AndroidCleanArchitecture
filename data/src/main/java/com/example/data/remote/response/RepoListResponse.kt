package com.example.data.remote.response


import com.google.gson.annotations.SerializedName

data class RepoListResponse(
    @SerializedName("total_count")
    val totalCount: Int?,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean?,
    @SerializedName("items")
    val items: List<Item?>?
) {
    data class Item(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("owner")
        val owner: Owner?,
        @SerializedName("description")
        val description: String?,
    )

    data class Owner(
        @SerializedName("login")
        val login: String?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("avatar_url")
        val avatarUrl: String?,
    )
}