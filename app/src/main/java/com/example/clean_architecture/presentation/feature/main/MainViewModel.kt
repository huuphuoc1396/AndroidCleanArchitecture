package com.example.clean_architecture.presentation.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.clean_architecture.core.platform.BaseViewModel
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

    private val _repoItems = MutableLiveData<List<RepoItem>>(listOf())
    val repoItems: LiveData<List<RepoItem>> = _repoItems

    val isNoResults: LiveData<Boolean> = Transformations.map(repoItems) {
        it.isNullOrEmpty()
    }

    fun searchRepos(text: String) {
        val query = text.trim().lowercase()
        if (query.isNotEmpty()) {
            searchJob?.cancel()
            searchJob = viewModelScope.launch {
                setLoading(true)
                searchRepos(SearchRepos.Params(text))
                    .map { repoItemMapper.mapList(it) }
                    .fold(::handleNetworkError) { _repoItems.value = it }
                setLoading(false)
            }
        }
    }
}