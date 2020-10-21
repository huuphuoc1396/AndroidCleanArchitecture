package com.example.presentation.mapper

import com.example.domain.model.Repo
import com.example.lib.mapper.Mapper
import com.example.presentation.model.OwnerItem
import com.example.presentation.model.RepoItem

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