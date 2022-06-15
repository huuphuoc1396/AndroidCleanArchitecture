package com.example.clean_architecture.domain.model

data class RepoModel(
    val id: Int,
    val name: String,
    val description: String,
    val ownerModel: OwnerModel,
)