package com.example.clean_architecture.data.remote.mapper

import com.example.clean_architecture.data.remote.response.ItemResponse
import com.example.clean_architecture.domain.core.extension.defaultEmpty
import com.example.clean_architecture.domain.core.extension.defaultZero
import com.example.clean_architecture.domain.core.mapper.Mapper
import com.example.clean_architecture.domain.model.Repo
import javax.inject.Inject

class RepoMapper @Inject constructor(
    private val ownerMapper: OwnerMapper,
) : Mapper<ItemResponse?, Repo>() {
    override fun map(input: ItemResponse?): Repo {
        val owner = ownerMapper.map(input?.owner)
        return Repo(
            id = input?.id.defaultZero(),
            name = input?.name.defaultEmpty(),
            description = input?.description.defaultEmpty(),
            owner = owner
        )
    }
}