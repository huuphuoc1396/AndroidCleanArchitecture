package com.example.clean_architecture.presentation.feature.main.model

data class RepoItem(
    val id: Int,
    val name: String,
    val description: String,
    val owner: OwnerItem
)