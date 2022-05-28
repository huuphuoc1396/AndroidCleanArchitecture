package com.example.clean_architecture.presentation.feature.main.list

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.clean_architecture.R
import com.example.clean_architecture.presentation.feature.main.model.OwnerItem
import com.example.clean_architecture.presentation.feature.main.model.RepoItem


@Preview(showBackground = true)
@Composable
fun PreviewRepoItem() {
    RepoItem(
        item = RepoItem(
            id = 0,
            name = "Android",
            description = "Android repository",
            owner = OwnerItem(
                id = 0,
                login = "me",
                avatarUrl = ""
            )
        ),
        Modifier.fillMaxWidth()
    )
}

@Composable
fun RepoItem(item: RepoItem, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier.size(64.dp),
            model = item.owner.avatarUrl,
            placeholder = painterResource(id = R.drawable.ic_baseline_image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(
                text = item.name,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.subtitle1
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = stringResource(id = R.string.by_owner, item.owner.login),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.caption
            )
        }
    }
}