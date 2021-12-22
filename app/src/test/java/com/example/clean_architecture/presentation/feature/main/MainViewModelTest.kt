package com.example.clean_architecture.presentation.feature.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.clean_architecture.domain.core.error.ApiError
import com.example.clean_architecture.domain.core.error.CoroutineError
import com.example.clean_architecture.domain.core.extension.nextString
import com.example.clean_architecture.domain.core.functional.Either
import com.example.clean_architecture.domain.model.Owner
import com.example.clean_architecture.domain.model.Repo
import com.example.clean_architecture.domain.usecase.SearchRepos
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

    private val searchRepos: SearchRepos = mockk()
    private val repoItemMapper: RepoItemMapper = mockk()

    private val repoItemObserver: Observer<List<RepoItem>> = mockk(relaxed = true)
    private val isLoadingObserver: Observer<Boolean> = mockk(relaxed = true)
    private val isNoResultsObserver: Observer<Boolean> = mockk(relaxed = true)
    private val networkErrorObserver: Observer<CoroutineError> = mockk(relaxed = true)

    @Test
    fun `searchRepos query isn't empty and repoList isn't empty`() {
        val mainViewModel = MainViewModel(
            searchRepos = searchRepos,
            repoItemMapper = repoItemMapper
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
            searchRepos(params = SearchRepos.Params(query.lowercase()))
        } returns Either.Right(repoList)

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
            searchRepos = searchRepos,
            repoItemMapper = repoItemMapper
        )
        val query = Random.nextString()
        val repoList: List<Repo> = listOf()
        val repoItemList: List<RepoItem> = listOf()

        coEvery {
            searchRepos(params = SearchRepos.Params(query.lowercase()))
        } returns Either.Right(repoList)

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
            searchRepos = searchRepos,
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
            searchRepos = searchRepos,
            repoItemMapper = repoItemMapper
        )
        val query = Random.nextString()
        val error = ApiError.ConnectionError

        coEvery {
            searchRepos(params = SearchRepos.Params(query.lowercase()))
        } returns Either.Left(error)

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