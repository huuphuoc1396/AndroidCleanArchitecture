package com.example.presentation.mapper

import com.example.domain.model.Owner
import com.example.domain.model.Repo
import com.example.presentation.model.OwnerItem
import com.example.presentation.model.RepoItem
import com.tngtech.java.junit.dataprovider.DataProvider
import com.tngtech.java.junit.dataprovider.DataProviderRunner
import com.tngtech.java.junit.dataprovider.UseDataProvider
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(DataProviderRunner::class)
class RepoItemMapperTest {

    private val repoItemMapper = RepoItemMapper()

    @Test
    @UseDataProvider("dataProvider")
    fun map(repo: Repo, expected: RepoItem) {
        val actual = repoItemMapper.map(repo)
        Assert.assertEquals(expected, actual)
    }

    companion object {
        @JvmStatic
        @DataProvider
        fun dataProvider() = listOf(
            listOf(
                Repo(
                    id = 82128465,
                    name = "Android",
                    description = "Android App Example",
                    owner = Owner(
                        id = 82128465,
                        avatarUrl = "https://avatars2.githubusercontent.com/u/23095877?v=4",
                        login = "open-android"
                    )
                ),
                RepoItem(
                    id = 82128465,
                    name = "Android",
                    description = "Android App Example",
                    owner = OwnerItem(
                        id = 82128465,
                        avatarUrl = "https://avatars2.githubusercontent.com/u/23095877?v=4",
                        login = "open-android"
                    )
                )
            )
        )
    }
}