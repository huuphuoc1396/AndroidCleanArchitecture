package com.example.clean_architecture.presentation.feature.main.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.clean_architecture.presentation.feature.main.MainViewModel
import com.example.clean_architecture.presentation.feature.main.model.RepoItem

@Composable
fun RepoList(
    mainViewModel: MainViewModel,
    onItemClick: (RepoItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val list: List<RepoItem> by mainViewModel.repoItems.observeAsState(listOf())
    LazyColumn(modifier = modifier) {
        items(list) { item ->
            RepoItem(item = item, Modifier.clickable {
                onItemClick(item)
            })
        }
    }
}