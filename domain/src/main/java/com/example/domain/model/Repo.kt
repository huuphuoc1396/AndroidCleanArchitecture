package com.example.domain.model

import com.example.domain.model.Owner

data class Repo(
    val id: Int,
    val name: String,
    val description: String,
    val owner: Owner
)