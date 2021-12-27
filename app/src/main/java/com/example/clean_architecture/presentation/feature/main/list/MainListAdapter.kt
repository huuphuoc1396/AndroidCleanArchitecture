package com.example.clean_architecture.presentation.feature.main.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.clean_architecture.core.platform.BaseBindingListAdapter
import com.example.clean_architecture.core.platform.BaseViewHolder
import com.example.clean_architecture.databinding.ItemRepoBinding
import com.example.clean_architecture.presentation.feature.main.model.RepoItem

class MainListAdapter(
    private val onItemClickListener: (RepoItem) -> Unit,
) : BaseBindingListAdapter<RepoItem>(RepoItemDiffCallback()) {

    override fun createBindingViewHolder(
        viewType: Int,
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<RepoItem> {
        val itemRepoBinding = ItemRepoBinding.inflate(layoutInflater, parent, false)
        return RepoViewHolder(itemRepoBinding, onItemClickListener)
    }

}