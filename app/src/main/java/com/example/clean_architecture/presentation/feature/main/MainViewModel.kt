package com.example.clean_architecture.presentation.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.clean_architecture.core.platform.BaseViewModel
import com.example.clean_architecture.domain.core.functional.map
import com.example.clean_architecture.domain.core.functional.onFailure
import com.example.clean_architecture.domain.core.functional.onSuccess
import com.example.clean_architecture.domain.core.interactor.params.EmptyParams
import com.example.clean_architecture.domain.usecase.IsFirstRunUseCase
import com.example.clean_architecture.domain.usecase.SearchReposUseCase
import com.example.clean_architecture.domain.usecase.SetFirstRunUseCase
import com.example.clean_architecture.presentation.feature.main.mapper.RepoItemMapper
import com.example.clean_architecture.presentation.feature.main.model.RepoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val isFirstRunUseCase: IsFirstRunUseCase,
    private val setFirstRunUseCase: SetFirstRunUseCase,
    private val searchReposUseCase: SearchReposUseCase,
    private val repoItemMapper: RepoItemMapper,
) : BaseViewModel() {

    private var searchJob: Job? = null

    private val _repoItems = MutableLiveData<List<RepoItem>>()
    val repoItems: LiveData<List<RepoItem>> = _repoItems

    val isNoResults: LiveData<Boolean> = Transformations.map(isLoading) { isLoading ->
        repoItems.value.isNullOrEmpty() && !isLoading
    }

    private val _firstRunChecking = MutableLiveData<Boolean>()
    val firstRunChecking: LiveData<Boolean> = _firstRunChecking

    init {
        checkFirstRun()
    }

    fun searchRepos(text: String) {
        val query = text.trim().lowercase()
        if (query.isNotEmpty()) {
            searchJob?.cancel()
            searchJob = viewModelScope.launch {
                setLoading(true)
                searchReposUseCase(SearchReposUseCase.Params(query))
                    .map { repoItemMapper.mapList(it) }
                    .onSuccess { _repoItems.value = it }
                    .onFailure { handleFailure(it) }
                setLoading(false)
            }
        }
    }

    fun setFistRun() {
        viewModelScope.launch { setFirstRunUseCase(EmptyParams) }
    }

    private fun checkFirstRun() {
        viewModelScope.launch {
            isFirstRunUseCase(EmptyParams)
                .onSuccess { _firstRunChecking.value = it }
                .onFailure { handleFailure(it) }
        }
    }
}