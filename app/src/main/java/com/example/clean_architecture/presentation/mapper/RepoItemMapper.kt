package com.example.clean_architecture.presentation.mapper

import com.example.clean_architecture.presentation.model.OwnerItem
import com.example.clean_architecture.presentation.model.RepoItem
import com.example.common_lib.mapper.Mapper
import com.example.domain.model.Repo

class RepoItemMapper : Mapper<Repo, RepoItem>() {
    override fun map(input: Repo): RepoItem {
        val ownerItem = OwnerItem(
            id = input.owner.id,
            login = input.owner.login,
            avatarUrl = input.owner.avatarUrl
        )
        return RepoItem(
            id = input.id,
            name = input.name,
            description = input.description,
            owner = ownerItem
        )
    }
}