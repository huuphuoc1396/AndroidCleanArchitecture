package com.example.data.remote.api

import com.example.common_lib.extension.nextString
import com.example.data.remote.api.common.BaseApiTest
import com.example.data.remote.api.common.HttpMethod
import com.example.data.remote.di.createRetrofit
import com.example.data.remote.interceptor.HeaderInterceptor
import com.example.data.remote.response.ItemResponse
import com.example.data.remote.response.OwnerResponse
import com.example.data.remote.response.RepoListResponse
import com.example.data.remote.response.ServerErrorResponse
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import kotlin.random.Random

class RepoApiTest : BaseApiTest() {

    private lateinit var repoApi: RepoApi

    private val repoListResponse = RepoListResponse(
        totalCount = 3,
        incompleteResults = false,
        items = listOf(
            ItemResponse(
                id = 82128465,
                name = "Android",
                description = "GitHub上最火的Android开源项目,所有开源项目都有详细资料和配套视频",
                owner = OwnerResponse(
                    id = 23095877,
                    login = "open-android",
                    avatarUrl = "https://avatars2.githubusercontent.com/u/23095877?v=4"
                )
            ),
            ItemResponse(
                id = 12544093,
                name = "Android",
                description = "Android related examples",
                owner = OwnerResponse(
                    id = 3790597,
                    login = "hmkcode",
                    avatarUrl = "https://avatars3.githubusercontent.com/u/3790597?v=4"
                )
            ),
            ItemResponse(
                id = 190503465,
                name = "hilahi-music",
                description = null,
                owner = OwnerResponse(
                    id = 21271482,
                    login = "huuphuoc1396",
                    avatarUrl = "https://avatars3.githubusercontent.com/u/21271482?v=4"
                )
            )
        )
    )

    private val serverErrorResponse = ServerErrorResponse(
        message = "Validation Failed",
        errors = listOf(
            linkedMapOf(
                "resource" to "Search",
                "field" to "q",
                "code" to "missing"
            )
        )
    )

    override fun onStartMockServer(url: String) {
        super.onStartMockServer(url)
        repoApi = createRetrofit(
            baseUrl = url,
            connectionTimeout = 15000,
            headerInterceptor = HeaderInterceptor(),
            chuckerInterceptor = mockk(),
            loggingInterceptor = mockk(),
            isLoggingEnable = false
        ).create(RepoApi::class.java)
    }

    @Test
    fun `searchRepos is success`() = runBlocking {
        val query = Random.nextString()

        mockHttpResponse(
            responseCode = 200,
            fileName = "search_repos/search_repos_success.json",
            path = "search/repositories?q=$query",
            method = HttpMethod.GET
        )

        Assert.assertEquals(repoListResponse, repoApi.searchRepos(query))
    }

    @Test
    fun `searchRepos is error`() = runBlocking {
        val query = Random.nextString()

        mockHttpResponse(
            responseCode = 422,
            fileName = "search_repos/search_repos_error.json",
            path = "search/repositories?q=$query",
            method = HttpMethod.GET
        )

        try {
            repoApi.searchRepos(query)
            Assert.assertTrue(false)
        } catch (exception: Exception) {
            assertHttpException(exception, serverErrorResponse)
        }
    }
}