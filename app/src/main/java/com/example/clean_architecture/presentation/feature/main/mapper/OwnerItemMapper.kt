package com.example.clean_architecture.presentation.feature.main.mapper

import com.example.clean_architecture.domain.core.mapper.Mapper
import com.example.clean_architecture.domain.model.OwnerModel
import com.example.clean_architecture.presentation.feature.main.model.OwnerItem
import javax.inject.Inject

class OwnerItemMapper @Inject constructor() : Mapper<OwnerModel, OwnerItem>() {
    override fun map(input: OwnerModel): OwnerItem {
        return OwnerItem(
            id = input.id,
            login = input.login,
            avatarUrl = input.avatarUrl,
        )
    }
}