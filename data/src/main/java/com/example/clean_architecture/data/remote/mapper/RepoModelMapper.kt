package com.example.clean_architecture.data.remote.mapper

import com.example.clean_architecture.data.remote.response.ItemResponse
import com.example.clean_architecture.domain.core.extension.defaultEmpty
import com.example.clean_architecture.domain.core.extension.defaultZero
import com.example.clean_architecture.domain.core.mapper.Mapper
import com.example.clean_architecture.domain.model.RepoModel
import javax.inject.Inject

class RepoModelMapper @Inject constructor(
    private val ownerModelMapper: OwnerModelMapper,
) : Mapper<ItemResponse?, RepoModel>() {
    override fun map(input: ItemResponse?): RepoModel {
        val owner = ownerModelMapper.map(input?.owner)
        return RepoModel(
            id = input?.id.defaultZero(),
            name = input?.name.defaultEmpty(),
            description = input?.description.defaultEmpty(),
            ownerModel = owner
        )
    }
}