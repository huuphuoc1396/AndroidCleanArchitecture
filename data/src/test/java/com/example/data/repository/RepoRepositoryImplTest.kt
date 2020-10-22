package com.example.data.repository

import com.example.common_lib.exception.ApiException
import com.example.common_lib.extension.nextString
import com.example.common_unit_test.assertError
import com.example.common_unit_test.assertSuccess
import com.example.common_unit_test.makeRandomInstance
import com.example.common_unit_test.makeRandomListInstance
import com.example.data.remote.api.RepoApi
import com.example.data.remote.exception.RemoteCoroutineExceptionHandler
import com.example.data.remote.mapper.RepoMapper
import com.example.data.remote.response.RepoListResponse
import com.example.domain.model.Repo
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
    private val remoteCoroutineExceptionHandler: RemoteCoroutineExceptionHandler = mockk()

    private val repoRepositoryImpl = RepoRepositoryImpl(
        repoApi = repoApi,
        repoMapper = repoMapper,
        remoteCoroutineExceptionHandler = remoteCoroutineExceptionHandler
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
        val error = ApiException.ConnectionException

        coEvery {
            repoApi.searchRepos(query)
        } throws exception

        every {
            remoteCoroutineExceptionHandler.handleException(exception)
        } returns error

        val resultWrapper = repoRepositoryImpl.searchRepos(query)

        resultWrapper.assertError(error)
    }
}