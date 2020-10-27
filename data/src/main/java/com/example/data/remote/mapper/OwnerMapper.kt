package com.example.data.remote.mapper

import com.example.common_lib.extension.defaultEmpty
import com.example.common_lib.extension.defaultZero
import com.example.common_lib.mapper.Mapper
import com.example.data.remote.response.OwnerResponse
import com.example.domain.model.Owner

class OwnerMapper : Mapper<OwnerResponse?, Owner>() {
    override fun map(input: OwnerResponse?): Owner {
        return Owner(
            id = input?.id.defaultZero(),
            login = input?.login.defaultEmpty(),
            avatarUrl = input?.avatarUrl.defaultEmpty()
        )
    }
}