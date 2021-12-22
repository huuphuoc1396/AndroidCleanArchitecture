package com.example.clean_architecture.presentation.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.clean_architecture.core.platform.BaseViewModel
import com.example.clean_architecture.domain.core.extension.defaultEmpty
import com.example.clean_architecture.domain.core.functional.map
import com.example.clean_architecture.domain.usecase.SearchRepos
import com.example.clean_architecture.presentation.feature.main.mapper.RepoItemMapper
import com.example.clean_architecture.presentation.feature.main.model.RepoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchRepos: SearchRepos,
    private val repoItemMapper: RepoItemMapper,
) : BaseViewModel() {

    private var searchJob: Job? = null

    val repoItems = MutableLiveData<List<RepoItem>>(listOf())
    val isLoading = MutableLiveData(false)
    val query = MutableLiveData("")

    val isNoResults: LiveData<Boolean> = Transformations.map(isLoading) { isLoading ->
        repoItems.value.isNullOrEmpty() && !query.value.isNullOrEmpty() && !isLoading
    }

    fun searchRepos() {
        val query = query.value.defaultEmpty().lowercase().trim()
        if (query.isNotEmpty()) {
            searchJob?.cancel()
            searchJob = viewModelScope.launch {
                isLoading.value = true
                searchRepos(SearchRepos.Params(query))
                    .map { repoItemMapper.mapList(it) }
                    .fold(::handleNetworkError) {
                        repoItems.value = it
                    }
                isLoading.value = false
            }
        }
    }
}