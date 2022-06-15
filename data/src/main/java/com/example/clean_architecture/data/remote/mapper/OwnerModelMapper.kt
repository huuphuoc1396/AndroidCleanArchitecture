package com.example.clean_architecture.data.remote.mapper

import com.example.clean_architecture.data.remote.response.OwnerResponse
import com.example.clean_architecture.domain.core.extension.defaultEmpty
import com.example.clean_architecture.domain.core.extension.defaultZero
import com.example.clean_architecture.domain.core.mapper.Mapper
import com.example.clean_architecture.domain.model.OwnerModel
import javax.inject.Inject

class OwnerModelMapper @Inject constructor() : Mapper<OwnerResponse?, OwnerModel>() {
    override fun map(input: OwnerResponse?): OwnerModel {
        return OwnerModel(
            id = input?.id.defaultZero(),
            login = input?.login.defaultEmpty(),
            avatarUrl = input?.avatarUrl.defaultEmpty()
        )
    }
}