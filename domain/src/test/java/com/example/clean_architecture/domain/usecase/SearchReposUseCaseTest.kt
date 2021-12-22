package com.example.clean_architecture.domain.usecase

import com.example.clean_architecture.domain.core.error.ApiError
import com.example.clean_architecture.domain.core.extension.nextString
import com.example.clean_architecture.domain.core.result.ResultWrapper
import com.example.clean_architecture.domain.model.Repo
import com.example.clean_architecture.domain.repository.RepoRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
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
        val repoList: List<Repo> = mockk()
        val expected = ResultWrapper.Success(repoList)

        coEvery {
            repoRepository.searchRepos(query)
        } returns expected

        val actual = searchReposUseCase.execute(
            params = SearchReposUseCase.Params(query)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `searchRepos is Error`() = runBlocking {
        val query = Random.nextString()
        val error = ApiError.ConnectionError
        val expected = ResultWrapper.Error(error)

        coEvery {
            repoRepository.searchRepos(query)
        } returns expected

        val actual = searchReposUseCase.execute(
            params = SearchReposUseCase.Params(query)
        )

        Assert.assertEquals(expected, actual)
    }
}