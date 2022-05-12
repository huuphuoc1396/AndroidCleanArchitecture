package com.example.clean_architecture.domain.usecase

import com.example.clean_architecture.domain.core.error.ApiFailure
import com.example.clean_architecture.domain.core.extension.nextString
import com.example.clean_architecture.domain.core.functional.ResultWrapper
import com.example.clean_architecture.domain.model.Repo
import com.example.clean_architecture.domain.repository.RepoRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import kotlin.random.Random

@ExperimentalStdlibApi
class SearchReposTest {
    private val repoRepository: RepoRepository = mockk()
    private val searchReposUseCase = SearchRepos(
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

        val actual = searchReposUseCase(
            params = SearchRepos.Params(query)
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `searchRepos is Error`() = runBlocking {
        val query = Random.nextString()
        val error = ApiFailure.Connection
        val expected = ResultWrapper.Failure(error)

        coEvery {
            repoRepository.searchRepos(query)
        } returns expected

        val actual = searchReposUseCase(
            params = SearchRepos.Params(query)
        )

        Assert.assertEquals(expected, actual)
    }
}