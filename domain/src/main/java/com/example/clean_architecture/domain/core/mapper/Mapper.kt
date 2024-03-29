package com.example.clean_architecture.domain.core.mapper

import com.example.clean_architecture.domain.core.extension.defaultEmpty

abstract class Mapper<Input, Output> {
    abstract fun map(input: Input): Output

    fun mapList(inputs: List<Input>?): List<Output> {
        return inputs?.map { input -> map(input) }.defaultEmpty()
    }
}