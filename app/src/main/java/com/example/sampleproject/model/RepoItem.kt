package com.example.sampleproject.model

data class RepoItem(
    val id: Int,
    val name: String,
    val description: String,
    val owner: OwnerItem
)