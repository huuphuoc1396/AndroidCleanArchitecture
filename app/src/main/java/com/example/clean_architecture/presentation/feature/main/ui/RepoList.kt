package com.example.clean_architecture.presentation.feature.main.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.clean_architecture.presentation.feature.main.model.OwnerItem
import com.example.clean_architecture.presentation.feature.main.model.RepoItem

val previewList: List<RepoItem> = List(10) { index ->
    RepoItem(
        id = index,
        name = "Android $index",
        description = "Android repository $index",
        owner = OwnerItem(
            id = index,
            login = "me",
            avatarUrl = "https://developer.android.com/images/logos/android.svg"
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewRepoList() {
    RepoList(
        list = previewList,
        onItemClick = {},
        modifier = Modifier.fillMaxWidth()
    )
}

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