package com.example.data.mapper

import com.example.data.response.RepoListResponse
import com.example.domain.model.Owner
import com.example.domain.model.Repo
import com.example.lib.extension.orEmpty
import com.example.lib.extension.orZero
import com.example.lib.mapper.Mapper

class RepoMapper : Mapper<RepoListResponse.Item, Repo>() {
    override fun map(input: RepoListResponse.Item): Repo {
        val owner = Owner(
            id = input.owner?.id.orZero(),
            login = input.owner?.login.orEmpty(),
            avatarUrl = input.owner?.avatarUrl.orEmpty()
        )

        return Repo(
            id = input.id.orZero(),
            name = input.name.orEmpty(),
            description = input.name.orEmpty(),
            owner = owner
        )
    }

}