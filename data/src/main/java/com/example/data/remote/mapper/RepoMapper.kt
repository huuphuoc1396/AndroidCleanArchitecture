package com.example.data.remote.mapper

import com.example.data.remote.response.RepoListResponse
import com.example.domain.model.Owner
import com.example.domain.model.Repo
import com.example.lib.extension.orEmpty
import com.example.lib.extension.orZero
import com.example.lib.mapper.Mapper

class RepoMapper(
    private val ownerMapper: OwnerMapper
) : Mapper<RepoListResponse.Item?, Repo>() {
    override fun map(input: RepoListResponse.Item?): Repo {
        return Repo(
            id = input?.id.orZero(),
            name = input?.name.orEmpty(),
            description = input?.name.orEmpty(),
            owner = ownerMapper.map(input?.owner)
        )
    }

}