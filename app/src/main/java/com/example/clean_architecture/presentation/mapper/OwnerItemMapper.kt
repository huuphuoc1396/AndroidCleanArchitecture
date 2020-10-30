package com.example.clean_architecture.presentation.mapper

import com.example.clean_architecture.presentation.model.OwnerItem
import com.example.common_lib.mapper.Mapper
import com.example.domain.model.Owner

class OwnerItemMapper : Mapper<Owner, OwnerItem>() {
    override fun map(input: Owner): OwnerItem {
        return OwnerItem(
            id = input.id,
            login = input.login,
            avatarUrl = input.avatarUrl
        )
    }
}