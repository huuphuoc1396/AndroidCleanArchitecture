package com.example.clean_architecture.data.repository

import com.example.clean_architecture.data.remote.error.RemoteCoroutineErrorHandler
import com.example.clean_architecture.data.remote.mapper.RepoMapper
import com.example.clean_architecture.data.remote.response.ItemResponse
import com.example.clean_architecture.data.remote.response.RepoListResponse
import com.example.clean_architecture.data.remote.retrofit.api.RepoApi
import com.example.clean_architecture.domain.core.error.ApiError
import com.example.clean_architecture.domain.core.extension.nextString
import com.example.clean_architecture.domain.core.functional.Either
import com.example.clean_architecture.domain.model.Repo
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import kotlin.random.Random

@ExperimentalStdlibApi
class RepoRepositoryImplTest {
    private val repoApi: RepoApi = mockk()
    private val repoMapper: RepoMapper = mockk()
    private val remoteCoroutineErrorHandler: RemoteCoroutineErrorHandler = mockk()

    private val repoRepositoryImpl = RepoRepositoryImpl(
        repoApi = repoApi,
        repoMapper = repoMapper,
        remoteCoroutineErrorHandler = remoteCoroutineErrorHandler
    )

    @Test
    fun `searchRepos is Success`() = runBlocking {
        val query = Random.nextString()
        val repoItemListResponse: List<ItemResponse> = mockk()
        val repoListResponse = RepoListResponse(
            totalCount = Random.nextInt(),
            incompleteResults = false,
            items = repoItemListResponse
        )
        val repoList: List<Repo> = mockk()

        coEvery {
            repoApi.searchRepos(query)
        } returns repoListResponse

        every {
            repoMapper.mapList(repoItemListResponse)
        } returns repoList

        val actual = repoRepositoryImpl.searchRepos(query)
        val expected = Either.Right(repoList)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `searchRepos is Error`() = runBlocking {
        val query = Random.nextString()
        val exception = Exception()
        val error = ApiError.ConnectionError

        coEvery {
            repoApi.searchRepos(query)
        } throws exception

        every {
            remoteCoroutineErrorHandler.handleException(exception)
        } returns error

        val actual = repoRepositoryImpl.searchRepos(query)
        val expected = Either.Left(error)
        Assert.assertEquals(expected, actual)
    }
}