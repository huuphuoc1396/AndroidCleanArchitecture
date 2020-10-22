package com.example.data.remote.mapper

import com.example.common_lib.extension.defaultEmpty
import com.example.common_lib.extension.defaultZero
import com.example.common_lib.mapper.Mapper
import com.example.data.remote.response.RepoListResponse
import com.example.domain.model.Owner
import com.example.domain.model.Repo

class RepoMapper : Mapper<RepoListResponse.Item?, Repo>() {
    override fun map(input: RepoListResponse.Item?): Repo {
        val owner = Owner(
            id = input?.id.defaultZero(),
            login = input?.owner?.login.defaultEmpty(),
            avatarUrl = input?.owner?.avatarUrl.defaultEmpty()
        )
        return Repo(
            id = input?.id.defaultZero(),
            name = input?.name.defaultEmpty(),
            description = input?.description.defaultEmpty(),
            owner = owner
        )
    }
}