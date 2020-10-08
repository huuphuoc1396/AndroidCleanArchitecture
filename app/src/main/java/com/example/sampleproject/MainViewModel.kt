package com.example.sampleproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleproject.mapper.RepoItemMapper
import com.example.sampleproject.model.RepoItem
import com.example.domain.usecase.SearchReposUseCase
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