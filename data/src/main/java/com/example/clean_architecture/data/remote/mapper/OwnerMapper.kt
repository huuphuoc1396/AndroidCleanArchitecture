package com.example.clean_architecture.data.remote.mapper

import com.example.clean_architecture.core_lib.extension.defaultEmpty
import com.example.clean_architecture.core_lib.extension.defaultZero
import com.example.clean_architecture.core_lib.mapper.Mapper
import com.example.clean_architecture.data.remote.response.OwnerResponse
import com.example.clean_architecture.domain.model.Owner

class OwnerMapper : Mapper<OwnerResponse?, Owner>() {
    override fun map(input: OwnerResponse?): Owner {
        return Owner(
            id = input?.id.defaultZero(),
            login = input?.login.defaultEmpty(),
            avatarUrl = input?.avatarUrl.defaultEmpty()
        )
    }
}