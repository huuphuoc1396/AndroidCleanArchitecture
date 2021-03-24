package com.example.clean_architecture.data.remote.mapper

import com.example.clean_architecture.data.remote.response.OwnerResponse
import com.example.clean_architecture.domain.model.Owner
import com.tngtech.java.junit.dataprovider.DataProvider
import com.tngtech.java.junit.dataprovider.DataProviderRunner
import com.tngtech.java.junit.dataprovider.UseDataProvider
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(DataProviderRunner::class)
class OwnerMapperTest {

    private val ownerMapper = OwnerMapper()

    @Test
    @UseDataProvider("dataProvider")
    fun map(response: OwnerResponse?, expected: Owner) {
        val actual = ownerMapper.map(response)
        Assert.assertEquals(expected, actual)
    }

    companion object {

        @JvmStatic
        @DataProvider
        fun dataProvider() = listOf(
            listOf(
                null,
                Owner(
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
                Owner(
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
                Owner(
                    id = 82128465,
                    avatarUrl = "https://avatars2.githubusercontent.com/u/23095877?v=4",
                    login = "open-android"
                )
            )
        )
    }
}