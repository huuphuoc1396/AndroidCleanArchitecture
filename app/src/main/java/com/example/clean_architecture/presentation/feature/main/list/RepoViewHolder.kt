package com.example.clean_architecture.presentation.feature.main.list

import com.example.clean_architecture.core.platform.BaseBindingViewHolder
import com.example.clean_architecture.databinding.ItemRepoBinding
import com.example.clean_architecture.presentation.feature.main.model.RepoItem

class RepoViewHolder(
    itemRepoBinding: ItemRepoBinding,
    onItemClickListener: (RepoItem) -> Unit,
) : BaseBindingViewHolder<RepoItem, ItemRepoBinding>(itemRepoBinding, onItemClickListener) {

    override fun onBind(item: RepoItem) {
        viewBinding.repoItem = item
    }
}