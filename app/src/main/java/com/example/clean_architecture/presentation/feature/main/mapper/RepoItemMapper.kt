package com.example.clean_architecture.presentation.feature.main.mapper

import com.example.clean_architecture.domain.core.mapper.Mapper
import com.example.clean_architecture.domain.model.Repo
import com.example.clean_architecture.presentation.feature.main.model.RepoItem
import javax.inject.Inject

class RepoItemMapper @Inject constructor(
    private val ownerItemMapper: OwnerItemMapper
) : Mapper<Repo, RepoItem>() {
    override fun map(input: Repo): RepoItem {
        val ownerItem = ownerItemMapper.map(input.owner)
        return RepoItem(
            id = input.id,
            name = input.name,
            description = input.description,
            owner = ownerItem,
        )
    }
}