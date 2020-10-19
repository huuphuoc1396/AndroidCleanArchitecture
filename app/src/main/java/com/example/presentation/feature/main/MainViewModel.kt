package com.example.presentation.feature.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.SearchReposUseCase
import com.example.presentation.mapper.RepoItemMapper
import com.example.presentation.model.RepoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class MainViewModel(
    private val searchReposUseCase: SearchReposUseCase,
    private val repoItemMapper: RepoItemMapper
) : ViewModel() {

    private var searchJob: Job? = null

    val repoItem = MutableLiveData<List<RepoItem>>()

    fun searchRepos(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                searchReposUseCase.execute(SearchReposUseCase.Params(query))
            }
            result.subscribe(
                success = { repos ->
                    repoItem.value = repoItemMapper.mapList(repos)
                },
                error = { exception ->
                    Timber.e(exception)
                }
            )
        }
    }
}