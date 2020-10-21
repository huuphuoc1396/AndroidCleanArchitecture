package com.example.data.remote.mapper

import com.example.common_unit_test.makeRandomInstance
import com.example.data.remote.response.RepoListResponse
import com.example.domain.model.Owner
import com.example.domain.model.Repo
import com.tngtech.java.junit.dataprovider.DataProvider
import com.tngtech.java.junit.dataprovider.DataProviderRunner
import com.tngtech.java.junit.dataprovider.UseDataProvider
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(DataProviderRunner::class)
class RepoMapperTest {

    private val ownerMapper = OwnerMapper()
    private val repoMapper = RepoMapper(ownerMapper)

    @Test
    @UseDataProvider("dataProvider")
    fun map(response: RepoListResponse.Item?, expected: Repo) {
        val actual = repoMapper.map(response)
        Assert.assertEquals(expected, actual)
    }

    companion object {
        private val owner: Owner = makeRandomInstance()

        @JvmStatic
        @DataProvider
        fun dataProvider() = listOf(
            listOf(
                null,
                Repo(
                    id = 0,
                    name = "",
                    description = "",
                    owner = Owner(
                        id = 0,
                        avatarUrl = "",
                        login = ""
                    )
                )
            ),

            listOf(
                RepoListResponse.Item(
                    id = null,
                    name = null,
                    description = null,
                    owner = null
                ),
                Repo(
                    id = 0,
                    name = "",
                    description = "",
                    owner = Owner(
                        id = 0,
                        avatarUrl = "",
                        login = ""
                    )
                )
            ),

            listOf(
                RepoListResponse.Item(
                    id = 82128465,
                    name = "Android",
                    description = "Android App Example",
                    owner = RepoListResponse.Owner(
                        id = 82128465,
                        avatarUrl = "https://avatars2.githubusercontent.com/u/23095877?v=4",
                        login = "open-android"
                    )
                ),
                Repo(
                    id = 82128465,
                    name = "Android",
                    description = "Android App Example",
                    owner = Owner(
                        id = 82128465,
                        avatarUrl = "https://avatars2.githubusercontent.com/u/23095877?v=4",
                        login = "open-android"
                    )
                )
            )
        )
    }
}