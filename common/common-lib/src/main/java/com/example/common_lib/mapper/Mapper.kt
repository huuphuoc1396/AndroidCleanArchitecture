package com.example.common_lib.mapper

import com.example.common_lib.extension.defaultEmpty

abstract class Mapper<Input, Output> {
    abstract fun map(input: Input): Output

    fun mapList(inputs: List<Input>?): List<Output> {
        return inputs?.map { input -> map(input) }.defaultEmpty()
    }
}