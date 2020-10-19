package com.example.presentation.feature.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.presentation.databinding.ItemRepoBinding
import com.example.presentation.model.RepoItem

class MainListAdapter : ListAdapter<RepoItem, RepoViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemRepoBinding = ItemRepoBinding.inflate(inflater, parent, false)
        return RepoViewHolder(itemRepoBinding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RepoItem>() {
            override fun areItemsTheSame(oldItem: RepoItem, newItem: RepoItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RepoItem, newItem: RepoItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}