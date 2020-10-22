package com.example.presentation.feature.main

import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.ItemRepoBinding
import com.example.presentation.model.RepoItem

class RepoViewHolder(
    private val itemRepoBinding: ItemRepoBinding
) : RecyclerView.ViewHolder(itemRepoBinding.root) {

    fun bind(item: RepoItem) {
        itemRepoBinding.repoItem = item
    }
}