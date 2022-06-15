package com.example.clean_architecture.presentation.mapper

import com.example.clean_architecture.domain.model.OwnerModel
import com.example.clean_architecture.domain.model.RepoModel
import com.example.clean_architecture.presentation.feature.main.mapper.OwnerItemMapper
import com.example.clean_architecture.presentation.feature.main.mapper.RepoItemMapper
import com.example.clean_architecture.presentation.feature.main.model.OwnerItem
import com.example.clean_architecture.presentation.feature.main.model.RepoItem
import com.tngtech.java.junit.dataprovider.DataProvider
import com.tngtech.java.junit.dataprovider.DataProviderRunner
import com.tngtech.java.junit.dataprovider.UseDataProvider
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(DataProviderRunner::class)
class RepoModelItemMapperTest {

    private val ownerItemMapper: OwnerItemMapper = mockk()

    private val repoItemMapper =
        RepoItemMapper(
            ownerItemMapper = ownerItemMapper
        )

    @Test
    @UseDataProvider("dataProvider")
    fun map(repoModel: RepoModel, expected: RepoItem) {
        every {
            ownerItemMapper.map(repoModel.ownerModel)
        } returns ownerItem
        val actual = repoItemMapper.map(repoModel)
        Assert.assertEquals(expected, actual)
    }

    companion object {
        private val ownerModel: OwnerModel = mockk()
        private val ownerItem: OwnerItem = mockk()

        @JvmStatic
        @DataProvider
        fun dataProvider() = listOf(
            listOf(
                RepoModel(
                    id = 82128465,
                    name = "Android",
                    description = "Android App Example",
                    ownerModel = ownerModel
                ),
                RepoItem(
                    id = 82128465,
                    name = "Android",
                    description = "Android App Example",
                    owner = ownerItem
                )
            )
        )
    }
}