package com.example.clean_architecture.data.remote.response

import com.google.gson.annotations.SerializedName

data class ServerErrorResponse(
    @SerializedName("message")
    val message: String?,
    @SerializedName("errors")
    val errors: List<Any>?,
)