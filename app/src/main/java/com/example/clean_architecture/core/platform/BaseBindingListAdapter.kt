package com.example.clean_architecture.core.platform

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class BaseBindingListAdapter<Item>(
    diffCallback: DiffUtil.ItemCallback<Item>
) : ListAdapter<Item, BaseViewHolder<Item>>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Item> {
        val layoutInflater = LayoutInflater.from(parent.context)
        return createBindingViewHolder(viewType, layoutInflater, parent)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Item>, position: Int) {
        holder.bind(getItem(position))
    }

    abstract fun createBindingViewHolder(
        viewType: Int,
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<Item>
}