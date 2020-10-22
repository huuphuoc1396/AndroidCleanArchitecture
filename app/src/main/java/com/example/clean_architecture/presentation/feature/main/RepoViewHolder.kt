package com.example.clean_architecture.presentation.feature.main

import androidx.recyclerview.widget.RecyclerView
import com.example.clean_architecture.databinding.ItemRepoBinding
import com.example.clean_architecture.presentation.model.RepoItem

class RepoViewHolder(
    private val itemRepoBinding: ItemRepoBinding
) : RecyclerView.ViewHolder(itemRepoBinding.root) {

    fun bind(item: RepoItem) {
        itemRepoBinding.repoItem = item
    }
}