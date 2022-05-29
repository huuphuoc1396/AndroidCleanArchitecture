package com.example.clean_architecture.presentation.feature.main.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.clean_architecture.presentation.feature.main.model.RepoItem

@Composable
fun RepoList(
    list: List<RepoItem>,
    onItemClick: (RepoItem) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(list) { item ->
            RepoItem(item = item, Modifier.clickable {
                onItemClick(item)
            })
        }
    }
}