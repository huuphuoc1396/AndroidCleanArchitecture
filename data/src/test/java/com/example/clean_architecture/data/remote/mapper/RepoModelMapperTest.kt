package com.example.clean_architecture.data.remote.mapper

import com.example.clean_architecture.data.remote.response.ItemResponse
import com.example.clean_architecture.data.remote.response.OwnerResponse
import com.example.clean_architecture.domain.model.OwnerModel
import com.example.clean_architecture.domain.model.RepoModel
import com.tngtech.java.junit.dataprovider.DataProvider
import com.tngtech.java.junit.dataprovider.DataProviderRunner
import com.tngtech.java.junit.dataprovider.UseDataProvider
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(DataProviderRunner::class)
class RepoModelMapperTest {

    private val ownerModelMapper: OwnerModelMapper = mockk()

    private val repoModelMapper = RepoModelMapper(
        ownerModelMapper = ownerModelMapper
    )

    @Test
    @UseDataProvider("dataProvider")
    fun map(response: ItemResponse?, expected: RepoModel) {
        every {
            ownerModelMapper.map(response?.owner)
        } returns ownerModel
        val actual = repoModelMapper.map(response)
        Assert.assertEquals(expected, actual)
    }

    companion object {
        private val ownerResponse: OwnerResponse = mockk()
        private val ownerModel: OwnerModel = mockk()

        @JvmStatic
        @DataProvider
        fun dataProvider() = listOf(
            listOf(
                null,
                RepoModel(
                    id = 0,
                    name = "",
                    description = "",
                    ownerModel = ownerModel
                )
            ),
            listOf(
                ItemResponse(
                    id = null,
                    name = null,
                    description = null,
                    owner = null
                ),
                RepoModel(
                    id = 0,
                    name = "",
                    description = "",
                    ownerModel = ownerModel
                )
            ),
            listOf(
                ItemResponse(
                    id = 82128465,
                    name = "Android",
                    description = "Android App Example",
                    owner = ownerResponse
                ),
                RepoModel(
                    id = 82128465,
                    name = "Android",
                    description = "Android App Example",
                    ownerModel = ownerModel
                )
            )
        )
    }
}