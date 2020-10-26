package com.example.data.remote.response

import com.google.gson.annotations.SerializedName

data class ItemResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("owner")
    val owner: OwnerResponse?,
    @SerializedName("description")
    val description: String?,
)