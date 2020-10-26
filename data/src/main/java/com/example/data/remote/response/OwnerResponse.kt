package com.example.data.remote.response

import com.google.gson.annotations.SerializedName

data class OwnerResponse(
    @SerializedName("login")
    val login: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
)