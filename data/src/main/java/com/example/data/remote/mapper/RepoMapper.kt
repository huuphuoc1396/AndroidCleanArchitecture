package com.example.data.remote.mapper

import com.example.common_lib.extension.defaultEmpty
import com.example.common_lib.extension.defaultZero
import com.example.common_lib.mapper.Mapper
import com.example.data.remote.response.ItemResponse
import com.example.domain.model.Repo

class RepoMapper(
    private val ownerMapper: OwnerMapper
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