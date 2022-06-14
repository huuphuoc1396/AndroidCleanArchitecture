package com.example.clean_architecture.presentation.feature.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.clean_architecture.domain.core.error.ApiFailure
import com.example.clean_architecture.domain.core.error.Failure
import com.example.clean_architecture.domain.core.extension.nextString
import com.example.clean_architecture.domain.core.functional.ResultWrapper
import com.example.clean_architecture.domain.core.interactor.params.EmptyParams
import com.example.clean_architecture.domain.model.Owner
import com.example.clean_architecture.domain.model.Repo
import com.example.clean_architecture.domain.usecase.IsFirstRunUseCase
import com.example.clean_architecture.domain.usecase.SearchReposUseCase
import com.example.clean_architecture.domain.usecase.SetFirstRunUseCase
import com.example.clean_architecture.presentation.feature.main.mapper.RepoItemMapper
import com.example.clean_architecture.presentation.feature.main.model.OwnerItem
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

    private val isFirstRunUseCase: IsFirstRunUseCase = mockk()
    private val setFirstRunUseCase: SetFirstRunUseCase = mockk()
    private val searchReposUseCase: SearchReposUseCase = mockk()
    private val repoItemMapper: RepoItemMapper = mockk()

    private val repoItemObserver: Observer<List<RepoItem>> = mockk(relaxed = true)
    private val isLoadingObserver: Observer<Boolean> = mockk(relaxed = true)
    private val isNoResultsObserver: Observer<Boolean> = mockk(relaxed = true)
    private val failureObserver: Observer<Failure> = mockk(relaxed = true)

    @Test
    fun `searchRepos query isn't empty and repoList isn't empty`() {
        coEvery {
            isFirstRunUseCase(EmptyParams)
        } returns ResultWrapper.Success(false)

        val mainViewModel = MainViewModel(
            isFirstRunUseCase = isFirstRunUseCase,
            setFirstRunUseCase = setFirstRunUseCase,
            searchReposUseCase = searchReposUseCase,
            repoItemMapper = repoItemMapper,
        )
        val query = Random.nextString()
        val repo1 = Repo(
            id = 1,
            name = "name 1",
            description = "description 1",
            owner = Owner(
                id = 1,
                login = "login 1",
                avatarUrl = "avatarUrl1"
            )
        )
        val repo2 = Repo(
            id = 2,
            name = "name 2",
            description = "description 2",
            owner = Owner(
                id = 2,
                login = "login 2",
                avatarUrl = "avatarUrl2"
            )
        )
        val repoList: List<Repo> = listOf(repo1, repo2)

        val repoItem1 = RepoItem(
            id = 1,
            name = "name 1",
            description = "description 1",
            owner = OwnerItem(
                id = 1,
                login = "login 1",
                avatarUrl = "avatarUrl1"
            )
        )
        val repoItem2 = RepoItem(
            id = 2,
            name = "name 2",
            description = "description 2",
            owner = OwnerItem(
                id = 2,
                login = "login 2",
                avatarUrl = "avatarUrl2"
            )
        )
        val repoItemList: List<RepoItem> = listOf(repoItem1, repoItem2)

        coEvery {
            searchReposUseCase(params = SearchReposUseCase.Params(query.lowercase()))
        } returns ResultWrapper.Success(repoList)

        every {
            repoItemMapper.mapList(repoList)
        } returns repoItemList

        registerObserver(mainViewModel)

        mainViewModel.searchRepos(query)

        verifyOrder {
            isLoadingObserver.onChanged(true)
            isNoResultsObserver.onChanged(false)
            repoItemObserver.onChanged(repoItemList)
            isLoadingObserver.onChanged(false)
            isNoResultsObserver.onChanged(false)

            failureObserver wasNot Called
        }
    }

    @Test
    fun `searchRepos query isn't empty and repoList is empty`() {
        coEvery {
            isFirstRunUseCase(EmptyParams)
        } returns ResultWrapper.Success(false)

        val mainViewModel = MainViewModel(
            isFirstRunUseCase = isFirstRunUseCase,
            setFirstRunUseCase = setFirstRunUseCase,
            searchReposUseCase = searchReposUseCase,
            repoItemMapper = repoItemMapper,
        )
        val query = Random.nextString()
        val repoList: List<Repo> = listOf()
        val repoItemList: List<RepoItem> = listOf()

        coEvery {
            searchReposUseCase(params = SearchReposUseCase.Params(query.lowercase()))
        } returns ResultWrapper.Success(repoList)

        every {
            repoItemMapper.mapList(repoList)
        } returns repoItemList

        registerObserver(mainViewModel)

        mainViewModel.searchRepos(query)

        verifyOrder {
            isLoadingObserver.onChanged(true)
            isNoResultsObserver.onChanged(false)
            repoItemObserver.onChanged(listOf())
            isLoadingObserver.onChanged(false)
            isNoResultsObserver.onChanged(true)

            failureObserver wasNot Called
        }
    }

    @Test
    fun `searchRepos query is empty`() {
        coEvery {
            isFirstRunUseCase(EmptyParams)
        } returns ResultWrapper.Success(false)

        val mainViewModel = MainViewModel(
            isFirstRunUseCase = isFirstRunUseCase,
            setFirstRunUseCase = setFirstRunUseCase,
            searchReposUseCase = searchReposUseCase,
            repoItemMapper = repoItemMapper,
        )
        val query = ""

        registerObserver(mainViewModel)

        mainViewModel.searchRepos(query)
    }

    @Test
    fun `searchRepos is error`() {
        coEvery {
            isFirstRunUseCase(EmptyParams)
        } returns ResultWrapper.Success(false)

        val mainViewModel = MainViewModel(
            isFirstRunUseCase = isFirstRunUseCase,
            setFirstRunUseCase = setFirstRunUseCase,
            searchReposUseCase = searchReposUseCase,
            repoItemMapper = repoItemMapper,
        )
        val query = Random.nextString()
        val error = ApiFailure.Connection

        coEvery {
            searchReposUseCase(params = SearchReposUseCase.Params(query.lowercase()))
        } returns ResultWrapper.Failure(error)

        registerObserver(mainViewModel)

        mainViewModel.searchRepos(query)

        verifyOrder {
            isLoadingObserver.onChanged(true)
            isNoResultsObserver.onChanged(false)
            failureObserver.onChanged(error)
            isLoadingObserver.onChanged(false)
            isNoResultsObserver.onChanged(true)
        }
    }

    private fun registerObserver(mainViewModel: MainViewModel) {
        mainViewModel.repoItems.observeForever(repoItemObserver)
        mainViewModel.isLoading.observeForever(isLoadingObserver)
        mainViewModel.isNoResults.observeForever(isNoResultsObserver)
        mainViewModel.failure.observeForever(failureObserver)
    }
}