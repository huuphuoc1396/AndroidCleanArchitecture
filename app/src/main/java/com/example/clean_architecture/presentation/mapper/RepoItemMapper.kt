package com.example.clean_architecture.presentation.mapper

import com.example.clean_architecture.presentation.model.RepoItem
import com.example.common_lib.mapper.Mapper
import com.example.domain.model.Repo

class RepoItemMapper(
    private val ownerItemMapper: OwnerItemMapper
) : Mapper<Repo, RepoItem>() {
    override fun map(input: Repo): RepoItem {
        val ownerItem = ownerItemMapper.map(input.owner)
        return RepoItem(
            id = input.id,
            name = input.name,
            description = input.description,
            owner = ownerItem
        )
    }
}