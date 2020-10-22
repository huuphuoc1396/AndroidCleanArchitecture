package com.example.domain.usecase

import com.example.common_unit_test.assertError
import com.example.common_unit_test.assertSuccess
import com.example.common_unit_test.makeRandomListInstance
import com.example.domain.model.Repo
import com.example.domain.repository.RepoRepository
import com.example.lib.exception.ApiException
import com.example.lib.extension.nextString
import com.example.lib.result.ResultWrapper
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.random.Random

@ExperimentalStdlibApi
class SearchReposUseCaseTest {
    private val repoRepository: RepoRepository = mockk()
    private val searchReposUseCase = SearchReposUseCase(
        repoRepository = repoRepository
    )

    @Test
    fun `searchRepos is Success`() = runBlocking {
        val query = Random.nextString()
        val repoList: List<Repo> = makeRandomListInstance(0, 100)

        coEvery {
            repoRepository.searchRepos(query)
        } returns ResultWrapper.Success(repoList)

        val resultWrapper = searchReposUseCase.execute(
            params = SearchReposUseCase.Params(query)
        )

        resultWrapper.assertSuccess(repoList)
    }

    @Test
    fun `searchRepos is Error`() = runBlocking {
        val query = Random.nextString()
        val error = ApiException.ConnectionException

        coEvery {
            repoRepository.searchRepos(query)
        } returns ResultWrapper.Error(error)

        val resultWrapper = searchReposUseCase.execute(
            params = SearchReposUseCase.Params(query)
        )

        resultWrapper.assertError(error)
    }
}