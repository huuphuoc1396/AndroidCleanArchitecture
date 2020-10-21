package com.example.presentation.feature.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.common_unit_test.makeRandomListInstance
import com.example.domain.model.Repo
import com.example.domain.usecase.SearchReposUseCase
import com.example.lib.exception.ApiException
import com.example.lib.exception.CoroutineException
import com.example.lib.extension.nextString
import com.example.lib.result.ResultWrapper
import com.example.presentation.mapper.RepoItemMapper
import com.example.presentation.model.RepoItem
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.random.Random

@ExperimentalStdlibApi
class MainViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val searchReposUseCase: SearchReposUseCase = mockk()
    private val repoItemMapper: RepoItemMapper = mockk()

    private val repoItemObserver: Observer<List<RepoItem>> = mockk(relaxed = true)
    private val isLoadingObserver: Observer<Boolean> = mockk(relaxed = true)
    private val isNoResultsObserver: Observer<Boolean> = mockk(relaxed = true)
    private val networkErrorObserver: Observer<CoroutineException> = mockk(relaxed = true)

    private val mainViewModel = MainViewModel(
        searchReposUseCase = searchReposUseCase,
        repoItemMapper = repoItemMapper
    )

    @Before
    fun setup() {
        mainViewModel.repoItem.observeForever(repoItemObserver)
        mainViewModel.isLoading.observeForever(isLoadingObserver)
        mainViewModel.isNoResults.observeForever(isNoResultsObserver)
        mainViewModel.networkError.observeForever(networkErrorObserver)
    }

    @Test
    fun `searchRepos query isn't empty and repoList isn't empty`() {
        clear()
        val query = Random.nextString()
        val repoList: List<Repo> = makeRandomListInstance(1, 10)
        val repoItemList: List<RepoItem> = makeRandomListInstance(1, 10)

        coEvery {
            searchReposUseCase.execute(
                params = SearchReposUseCase.Params(query.toLowerCase())
            )
        } returns ResultWrapper.Success(repoList)

        every {
            repoItemMapper.mapList(repoList)
        } returns repoItemList

        mainViewModel.query.value = query

        mainViewModel.searchRepos()

        verify {
            repoItemObserver.onChanged(repoItemList)
            isLoadingObserver.onChanged(false)
            isNoResultsObserver.onChanged(false)
        }
    }

    @Test
    fun `searchRepos query isn't empty and repoList is empty`() {
        clear()
        val query = Random.nextString()
        val repoList: List<Repo> = listOf()
        val repoItemList: List<RepoItem> = listOf()

        coEvery {
            searchReposUseCase.execute(
                params = SearchReposUseCase.Params(query.toLowerCase())
            )
        } returns ResultWrapper.Success(repoList)

        every {
            repoItemMapper.mapList(repoList)
        } returns repoItemList

        mainViewModel.query.value = query

        mainViewModel.searchRepos()

        verify {
            repoItemObserver.onChanged(repoItemList)
            isLoadingObserver.onChanged(false)
            isNoResultsObserver.onChanged(true)
        }
    }

    @Test
    fun `searchRepos query is empty`() {
        clear()
        val query = ""

        mainViewModel.searchRepos()

        verify {
            repoItemObserver.onChanged(listOf())
            isLoadingObserver.onChanged(false)
            isNoResultsObserver.onChanged(false)
        }
    }

    @Test
    fun `searchRepos is error`() {
        clear()
        val query = Random.nextString()
        val error = ApiException.ConnectionException

        coEvery {
            searchReposUseCase.execute(
                params = SearchReposUseCase.Params(query.toLowerCase())
            )
        } returns ResultWrapper.Error(error)

        mainViewModel.query.value = query

        mainViewModel.searchRepos()

        verify {
            isLoadingObserver.onChanged(false)
            isNoResultsObserver.onChanged(false)
            networkErrorObserver.onChanged(error)
        }
    }

    private fun clear() {
        mainViewModel.repoItem.value = listOf()
    }
}