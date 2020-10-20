package com.example.presentation.feature.main

import androidx.lifecycle.*
import com.example.domain.usecase.SearchReposUseCase
import com.example.lib.extension.orFalse
import com.example.presentation.mapper.RepoItemMapper
import com.example.presentation.model.RepoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.*

class MainViewModel(
    private val searchReposUseCase: SearchReposUseCase,
    private val repoItemMapper: RepoItemMapper
) : ViewModel() {

    private var searchJob: Job? = null

    val repoItem = MutableLiveData<List<RepoItem>>(listOf())
    val isLoading = MutableLiveData<Boolean>(false)
    val query = MutableLiveData<String>("")

    val isNoResults: LiveData<Boolean> = Transformations.map(isLoading) { isLoading ->
        repoItem.value.isNullOrEmpty() && !query.value.isNullOrEmpty() && !isLoading
    }

    fun searchRepos() {
        val query = query.value.orEmpty().toLowerCase(Locale.getDefault()).trim()
        if (query.isNotEmpty()) {
            searchJob?.cancel()
            searchJob = viewModelScope.launch {
                isLoading.value = true
                val result = withContext(Dispatchers.IO) {
                    searchReposUseCase.execute(SearchReposUseCase.Params(query))
                }
                result.subscribe(
                    success = { repos ->
                        repoItem.value = repoItemMapper.mapList(repos)
                        isLoading.value = false
                    },
                    error = { exception ->
                        Timber.e(exception)
                        isLoading.value = false
                    }
                )
            }
        }
    }
}