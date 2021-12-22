package com.example.clean_architecture.data.remote.mapper

import com.example.clean_architecture.data.remote.response.ItemResponse
import com.example.clean_architecture.data.remote.response.OwnerResponse
import com.example.clean_architecture.domain.model.Owner
import com.example.clean_architecture.domain.model.Repo
import com.tngtech.java.junit.dataprovider.DataProvider
import com.tngtech.java.junit.dataprovider.DataProviderRunner
import com.tngtech.java.junit.dataprovider.UseDataProvider
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(DataProviderRunner::class)
class RepoMapperTest {

    private val ownerMapper: OwnerMapper = mockk()

    private val repoMapper = RepoMapper(
        ownerMapper = ownerMapper
    )

    @Test
    @UseDataProvider("dataProvider")
    fun map(response: ItemResponse?, expected: Repo) {
        every {
            ownerMapper.map(response?.owner)
        } returns owner
        val actual = repoMapper.map(response)
        Assert.assertEquals(expected, actual)
    }

    companion object {
        private val ownerResponse: OwnerResponse = mockk()
        private val owner: Owner = mockk()

        @JvmStatic
        @DataProvider
        fun dataProvider() = listOf(
            listOf(
                null,
                Repo(
                    id = 0,
                    name = "",
                    description = "",
                    owner = owner
                )
            ),
            listOf(
                ItemResponse(
                    id = null,
                    name = null,
                    description = null,
                    owner = null
                ),
                Repo(
                    id = 0,
                    name = "",
                    description = "",
                    owner = owner
                )
            ),
            listOf(
                ItemResponse(
                    id = 82128465,
                    name = "Android",
                    description = "Android App Example",
                    owner = ownerResponse
                ),
                Repo(
                    id = 82128465,
                    name = "Android",
                    description = "Android App Example",
                    owner = owner
                )
            )
        )
    }
}