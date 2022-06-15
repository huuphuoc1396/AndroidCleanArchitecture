package com.example.clean_architecture.data.repository

import com.example.clean_architecture.data.remote.error.RemoteFailureHandler
import com.example.clean_architecture.data.remote.mapper.RepoModelMapper
import com.example.clean_architecture.data.remote.response.ItemResponse
import com.example.clean_architecture.data.remote.response.RepoListResponse
import com.example.clean_architecture.data.remote.retrofit.api.RepoApi
import com.example.clean_architecture.domain.core.error.ApiFailure
import com.example.clean_architecture.domain.core.extension.nextString
import com.example.clean_architecture.domain.core.functional.ResultWrapper
import com.example.clean_architecture.domain.model.RepoModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import kotlin.random.Random

@ExperimentalStdlibApi
class RepoModelRepositoryImplTest {
    private val repoApi: RepoApi = mockk()
    private val repoModelMapper: RepoModelMapper = mockk()
    private val remoteCoroutineErrorHandler: RemoteFailureHandler = mockk()

    private val repoRepositoryImpl = RepoRepositoryImpl(
        repoApi = repoApi,
        repoModelMapper = repoModelMapper,
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
        val repoModelList: List<RepoModel> = mockk()

        coEvery {
            repoApi.searchRepos(query)
        } returns repoListResponse

        every {
            repoModelMapper.mapList(repoItemListResponse)
        } returns repoModelList

        val actual = repoRepositoryImpl.searchRepos(query)
        val expected = ResultWrapper.Success(repoModelList)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `searchRepos is Error`() = runBlocking {
        val query = Random.nextString()
        val exception = Exception()
        val error = ApiFailure.Connection

        coEvery {
            repoApi.searchRepos(query)
        } throws exception

        every {
            remoteCoroutineErrorHandler.handleThrowable(exception)
        } returns error

        val actual = repoRepositoryImpl.searchRepos(query)
        val expected = ResultWrapper.Failure(error)
        Assert.assertEquals(expected, actual)
    }
}