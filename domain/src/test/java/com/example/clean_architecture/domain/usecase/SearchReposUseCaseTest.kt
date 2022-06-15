package com.example.clean_architecture.domain.usecase

import com.example.clean_architecture.domain.core.error.ApiFailure
import com.example.clean_architecture.domain.core.extension.nextString
import com.example.clean_architecture.domain.core.functional.ResultWrapper
import com.example.clean_architecture.domain.model.RepoModel
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
        val repoModelList: List<RepoModel> = mockk()
        val expected = ResultWrapper.Success(repoModelList)

        coEvery {
            repoRepository.searchRepos(query)
        } returns expected

        val actual = searchReposUseCase(
            params = SearchReposUseCase.Params(query)
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
            params = SearchReposUseCase.Params(query)
        )

        Assert.assertEquals(expected, actual)
    }
}