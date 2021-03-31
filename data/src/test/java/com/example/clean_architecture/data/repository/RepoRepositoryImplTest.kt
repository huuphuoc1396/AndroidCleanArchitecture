package com.example.clean_architecture.data.repository

import com.example.clean_architecture.core_lib.error.ApiError
import com.example.clean_architecture.core_lib.extension.nextString
import com.example.clean_architecture.core_unit_test.assertError
import com.example.clean_architecture.core_unit_test.assertSuccess
import com.example.clean_architecture.core_unit_test.makeRandomInstance
import com.example.clean_architecture.core_unit_test.makeRandomListInstance
import com.example.clean_architecture.data.remote.api.RepoApi
import com.example.clean_architecture.data.remote.error.RemoteCoroutineErrorHandler
import com.example.clean_architecture.data.remote.mapper.RepoMapper
import com.example.clean_architecture.data.remote.response.RepoListResponse
import com.example.clean_architecture.domain.model.Repo
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
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
        val repoListResponse: RepoListResponse = makeRandomInstance()
        val repoList: List<Repo> = makeRandomListInstance(0, 100)

        coEvery {
            repoApi.searchRepos(query)
        } returns repoListResponse

        every {
            repoMapper.mapList(repoListResponse.items)
        } returns repoList

        val resultWrapper = repoRepositoryImpl.searchRepos(query)

        resultWrapper.assertSuccess(repoList)
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

        val resultWrapper = repoRepositoryImpl.searchRepos(query)

        resultWrapper.assertError(error)
    }
}