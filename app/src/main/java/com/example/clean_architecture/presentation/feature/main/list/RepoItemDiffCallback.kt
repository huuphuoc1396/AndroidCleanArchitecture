package com.example.clean_architecture.presentation.feature.main.list

import androidx.recyclerview.widget.DiffUtil
import com.example.clean_architecture.presentation.feature.main.model.RepoItem
import javax.inject.Inject

class RepoItemDiffCallback @Inject constructor() : DiffUtil.ItemCallback<RepoItem>() {
    override fun areItemsTheSame(oldItem: RepoItem, newItem: RepoItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RepoItem, newItem: RepoItem): Boolean {
        return oldItem == newItem
    }
}