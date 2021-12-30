package com.example.clean_architecture.core.platform

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.clean_architecture.core.extension.setOnSingleClickListener

abstract class BaseViewHolder<Item>(
    itemView: View,
    onItemClickListener: (Item) -> Unit,
) : RecyclerView.ViewHolder(itemView) {
    var item: Item? = null

    init {
        this.itemView.setOnSingleClickListener {
            item?.let(onItemClickListener)
        }
    }

    protected abstract fun onBind(item: Item)

    fun bind(item: Item) {
        this.item = item
        onBind(item)
    }
}