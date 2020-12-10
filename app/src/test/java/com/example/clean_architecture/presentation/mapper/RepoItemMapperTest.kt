package com.example.clean_architecture.presentation.mapper

import com.example.clean_architecture.core_unit_test.makeRandomInstance
import com.example.clean_architecture.domain.model.Owner
import com.example.clean_architecture.domain.model.Repo
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
class RepoItemMapperTest {

    private val ownerItemMapper: OwnerItemMapper = mockk()

    private val repoItemMapper =
        RepoItemMapper(
            ownerItemMapper = ownerItemMapper
        )

    @Test
    @UseDataProvider("dataProvider")
    fun map(repo: Repo, expected: RepoItem) {
        every {
            ownerItemMapper.map(repo.owner)
        } returns ownerItem
        val actual = repoItemMapper.map(repo)
        Assert.assertEquals(expected, actual)
    }

    companion object {
        private val owner: Owner = makeRandomInstance()
        private val ownerItem: OwnerItem = makeRandomInstance()

        @JvmStatic
        @DataProvider
        fun dataProvider() = listOf(
            listOf(
                Repo(
                    id = 82128465,
                    name = "Android",
                    description = "Android App Example",
                    owner = owner
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