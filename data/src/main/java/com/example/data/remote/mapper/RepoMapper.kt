package com.example.data.remote.mapper

import com.example.data.remote.response.RepoListResponse
import com.example.domain.model.Repo
import com.example.lib.extension.defaultEmpty
import com.example.lib.extension.defaultZero
import com.example.lib.mapper.Mapper

class RepoMapper(
    private val ownerMapper: OwnerMapper
) : Mapper<RepoListResponse.Item?, Repo>() {
    override fun map(input: RepoListResponse.Item?): Repo {
        return Repo(
            id = input?.id.defaultZero(),
            name = input?.name.defaultEmpty(),
            description = input?.name.defaultEmpty(),
            owner = ownerMapper.map(input?.owner)
        )
    }

}