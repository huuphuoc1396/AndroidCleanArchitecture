package com.example.clean_architecture.presentation.feature.main

import androidx.recyclerview.widget.RecyclerView
import com.example.clean_architecture.core_android.extension.setOnSingleClickListener
import com.example.clean_architecture.presentation.databinding.ItemRepoBinding
import com.example.clean_architecture.presentation.feature.main.model.RepoItem

class RepoViewHolder(
    private val itemRepoBinding: ItemRepoBinding,
    private val onItemClickListener: (RepoItem) -> Unit
) : RecyclerView.ViewHolder(itemRepoBinding.root) {

    init {
        itemView.setOnSingleClickListener {
            itemRepoBinding.repoItem?.let { repoItem ->
                onItemClickListener(repoItem)
            }
        }
    }

    fun bind(item: RepoItem) {
        itemRepoBinding.repoItem = item
    }
}