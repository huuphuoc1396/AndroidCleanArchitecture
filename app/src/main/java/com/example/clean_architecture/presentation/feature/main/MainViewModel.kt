package com.example.clean_architecture.presentation.feature.main

import androidx.lifecycle.*
import com.example.clean_architecture.core.platform.BaseViewModel
import com.example.clean_architecture.domain.core.functional.getOrElse
import com.example.clean_architecture.domain.core.functional.map
import com.example.clean_architecture.domain.core.interactor.params.EmptyParams
import com.example.clean_architecture.domain.usecase.IsFirstRun
import com.example.clean_architecture.domain.usecase.SearchRepos
import com.example.clean_architecture.domain.usecase.SetFirstRun
import com.example.clean_architecture.presentation.feature.main.mapper.RepoItemMapper
import com.example.clean_architecture.presentation.feature.main.model.RepoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    isFirstRun: IsFirstRun,
    private val setFirstRun: SetFirstRun,
    private val searchRepos: SearchRepos,
    private val repoItemMapper: RepoItemMapper,
) : BaseViewModel() {

    private var searchJob: Job? = null

    private val _repoItems = MutableLiveData<List<RepoItem>>()
    val repoItems: LiveData<List<RepoItem>> = _repoItems

    val isNoResults: LiveData<Boolean> = Transformations.map(isLoading) { isLoading ->
        repoItems.value.isNullOrEmpty() && !isLoading
    }

    val firstRunChecking: LiveData<Boolean> = isFirstRun(EmptyParams)
        .asLiveData()
        .map { it.getOrElse(false) }

    fun searchRepos(text: String) {
        val query = text.trim().lowercase()
        if (query.isNotEmpty()) {
            searchJob?.cancel()
            searchJob = viewModelScope.launch {
                setLoading(true)
                searchRepos(SearchRepos.Params(text))
                    .map { repoItemMapper.mapList(it) }
                    .fold(::handleFailure) { _repoItems.value = it }
                setLoading(false)
            }
        }
    }

    fun setFistRun() {
        viewModelScope.launch { setFirstRun(EmptyParams) }
    }
}