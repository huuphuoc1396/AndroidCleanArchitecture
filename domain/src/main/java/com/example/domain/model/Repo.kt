package com.example.domain.model

data class Repo(
    val id: Int,
    val name: String,
    val description: String,
    val owner: Owner
)