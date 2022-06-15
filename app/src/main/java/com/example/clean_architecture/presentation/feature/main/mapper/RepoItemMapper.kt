package com.example.clean_architecture.presentation.feature.main.mapper

import com.example.clean_architecture.domain.core.mapper.Mapper
import com.example.clean_architecture.domain.model.RepoModel
import com.example.clean_architecture.presentation.feature.main.model.RepoItem
import javax.inject.Inject

class RepoItemMapper @Inject constructor(
    private val ownerItemMapper: OwnerItemMapper
) : Mapper<RepoModel, RepoItem>() {
    override fun map(input: RepoModel): RepoItem {
        val ownerItem = ownerItemMapper.map(input.ownerModel)
        return RepoItem(
            id = input.id,
            name = input.name,
            description = input.description,
            owner = ownerItem,
        )
    }
}