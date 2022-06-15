package com.example.clean_architecture.data.remote.mapper

import com.example.clean_architecture.data.remote.response.OwnerResponse
import com.example.clean_architecture.domain.model.OwnerModel
import com.tngtech.java.junit.dataprovider.DataProvider
import com.tngtech.java.junit.dataprovider.DataProviderRunner
import com.tngtech.java.junit.dataprovider.UseDataProvider
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(DataProviderRunner::class)
class OwnerModelMapperTest {

    private val ownerModelMapper = OwnerModelMapper()

    @Test
    @UseDataProvider("dataProvider")
    fun map(response: OwnerResponse?, expected: OwnerModel) {
        val actual = ownerModelMapper.map(response)
        Assert.assertEquals(expected, actual)
    }

    companion object {

        @JvmStatic
        @DataProvider
        fun dataProvider() = listOf(
            listOf(
                null,
                OwnerModel(
                    id = 0,
                    avatarUrl = "",
                    login = ""
                )
            ),
            listOf(
                OwnerResponse(
                    id = null,
                    avatarUrl = null,
                    login = null
                ),
                OwnerModel(
                    id = 0,
                    avatarUrl = "",
                    login = ""
                )
            ),
            listOf(
                OwnerResponse(
                    id = 82128465,
                    avatarUrl = "https://avatars2.githubusercontent.com/u/23095877?v=4",
                    login = "open-android"
                ),
                OwnerModel(
                    id = 82128465,
                    avatarUrl = "https://avatars2.githubusercontent.com/u/23095877?v=4",
                    login = "open-android"
                )
            )
        )
    }
}