package com.example.clean_architecture.presentation.feature.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.clean_architecture.domain.core.error.ApiError
import com.example.clean_architecture.domain.core.error.CoroutineError
import com.example.clean_architecture.domain.core.extension.nextString
import com.example.clean_architecture.domain.core.result.ResultWrapper
import com.example.clean_architecture.core_unit_test.makeRandomListInstance
import com.example.clean_architecture.domain.model.Repo
import com.example.clean_architecture.domain.usecase.SearchReposUseCase
import com.example.clean_architecture.presentation.feature.main.mapper.RepoItemMapper
import com.example.clean_architecture.presentation.feature.main.model.RepoItem
import io.mockk.*
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
    private val networkErrorObserver: Observer<CoroutineError> = mockk(relaxed = true)

    @Test
    fun `searchRepos query isn't empty and repoList isn't empty`() {
        val mainViewModel = MainViewModel(
            searchReposUseCase = searchReposUseCase,
            repoItemMapper = repoItemMapper
        )
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

        registerObserver(mainViewModel)

        mainViewModel.query.value = query

        mainViewModel.searchRepos()

        verifyOrder {
            // Initializing
            repoItemObserver.onChanged(listOf())
            isLoadingObserver.onChanged(false)
            isNoResultsObserver.onChanged(false)

            // Start search repos
            isLoadingObserver.onChanged(true)
            isNoResultsObserver.onChanged(false)
            repoItemObserver.onChanged(repoItemList)
            isLoadingObserver.onChanged(false)
            isNoResultsObserver.onChanged(false)

            networkErrorObserver wasNot Called
        }
    }

    @Test
    fun `searchRepos query isn't empty and repoList is empty`() {
        val mainViewModel = MainViewModel(
            searchReposUseCase = searchReposUseCase,
            repoItemMapper = repoItemMapper
        )
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

        registerObserver(mainViewModel)

        mainViewModel.query.value = query

        mainViewModel.searchRepos()

        verifyOrder {
            // Initializing
            repoItemObserver.onChanged(listOf())
            isLoadingObserver.onChanged(false)
            isNoResultsObserver.onChanged(false)

            // Start search repos
            isLoadingObserver.onChanged(true)
            isNoResultsObserver.onChanged(false)
            repoItemObserver.onChanged(listOf())
            isLoadingObserver.onChanged(false)
            isNoResultsObserver.onChanged(true)

            networkErrorObserver wasNot Called
        }
    }

    @Test
    fun `searchRepos query is empty`() {
        val mainViewModel = MainViewModel(
            searchReposUseCase = searchReposUseCase,
            repoItemMapper = repoItemMapper
        )
        val query = ""

        registerObserver(mainViewModel)

        mainViewModel.query.value = query

        mainViewModel.searchRepos()

        verifyOrder {
            // Initializing
            repoItemObserver.onChanged(listOf())
            isLoadingObserver.onChanged(false)
            isNoResultsObserver.onChanged(false)

            networkErrorObserver wasNot Called
        }
    }

    @Test
    fun `searchRepos is error`() {
        val mainViewModel = MainViewModel(
            searchReposUseCase = searchReposUseCase,
            repoItemMapper = repoItemMapper
        )
        val query = Random.nextString()
        val error = ApiError.ConnectionError

        coEvery {
            searchReposUseCase.execute(
                params = SearchReposUseCase.Params(query.toLowerCase())
            )
        } returns ResultWrapper.Error(error)

        registerObserver(mainViewModel)

        mainViewModel.query.value = query

        mainViewModel.searchRepos()

        verifyOrder {
            // Initializing
            repoItemObserver.onChanged(listOf())
            isLoadingObserver.onChanged(false)
            isNoResultsObserver.onChanged(false)

            // Start search repos
            isLoadingObserver.onChanged(true)
            isNoResultsObserver.onChanged(false)
            networkErrorObserver.onChanged(error)
            isLoadingObserver.onChanged(false)
            isNoResultsObserver.onChanged(true)
        }
    }

    private fun registerObserver(mainViewModel: MainViewModel) {
        mainViewModel.repoItems.observeForever(repoItemObserver)
        mainViewModel.isLoading.observeForever(isLoadingObserver)
        mainViewModel.isNoResults.observeForever(isNoResultsObserver)
        mainViewModel.networkError.observeForever(networkErrorObserver)
    }
}