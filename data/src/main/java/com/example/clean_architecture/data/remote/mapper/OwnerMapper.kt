package com.example.clean_architecture.data.remote.mapper

import com.example.clean_architecture.core_lib.extension.defaultEmpty
import com.example.clean_architecture.core_lib.extension.defaultZero
import com.example.clean_architecture.core_lib.mapper.Mapper
import com.example.clean_architecture.data.remote.response.OwnerResponse
import com.example.clean_architecture.domain.model.Owner
import javax.inject.Inject

class OwnerMapper @Inject constructor() : Mapper<OwnerResponse?, Owner>() {
    override fun map(input: OwnerResponse?): Owner {
        return Owner(
            id = input?.id.defaultZero(),
            login = input?.login.defaultEmpty(),
            avatarUrl = input?.avatarUrl.defaultEmpty()
        )
    }
}