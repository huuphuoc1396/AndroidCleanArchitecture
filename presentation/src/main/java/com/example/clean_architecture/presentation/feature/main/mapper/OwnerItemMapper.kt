package com.example.clean_architecture.presentation.feature.main.mapper

import com.example.clean_architecture.core_lib.mapper.Mapper
import com.example.clean_architecture.domain.model.Owner
import com.example.clean_architecture.presentation.feature.main.model.OwnerItem

class OwnerItemMapper : Mapper<Owner, OwnerItem>() {
    override fun map(input: Owner): OwnerItem {
        return OwnerItem(
            id = input.id,
            login = input.login,
            avatarUrl = input.avatarUrl,
        )
    }
}