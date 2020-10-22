package com.example.clean_architecture.presentation.feature.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.clean_architecture.presentation.mapper.RepoItemMapper
import com.example.clean_architecture.presentation.model.RepoItem
import com.example.common_lib.exception.ApiException
import com.example.common_lib.exception.CoroutineException
import com.example.common_lib.extension.nextString
import com.example.common_lib.result.ResultWrapper
import com.example.common_unit_test.makeRandomListInstance
import com.example.domain.model.Repo
import com.example.domain.usecase.SearchReposUseCase
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
    private val networkErrorObserver: Observer<CoroutineException> = mockk(relaxed = true)

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
        val error = ApiException.ConnectionException

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
        mainViewModel.repoItem.observeForever(repoItemObserver)
        mainViewModel.isLoading.observeForever(isLoadingObserver)
        mainViewModel.isNoResults.observeForever(isNoResultsObserver)
        mainViewModel.networkError.observeForever(networkErrorObserver)
    }
}