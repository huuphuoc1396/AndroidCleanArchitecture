package com.example.data.remote.mapper

import com.example.data.remote.response.RepoListResponse
import com.example.domain.model.Owner
import com.example.domain.model.Repo
import com.example.lib.extension.defaultEmpty
import com.example.lib.extension.defaultZero
import com.example.lib.mapper.Mapper

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