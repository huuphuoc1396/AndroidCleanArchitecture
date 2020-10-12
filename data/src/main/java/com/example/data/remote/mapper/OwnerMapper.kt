package com.example.data.remote.mapper

import com.example.data.remote.response.RepoListResponse
import com.example.domain.model.Owner
import com.example.lib.extension.orEmpty
import com.example.lib.extension.orZero
import com.example.lib.mapper.Mapper

class OwnerMapper : Mapper<RepoListResponse.Owner?, Owner>() {
    override fun map(input: RepoListResponse.Owner?): Owner {
        return Owner(
            id = input?.id.orZero(),
            login = input?.login.orEmpty(),
            avatarUrl = input?.avatarUrl.orEmpty()
        )
    }
}