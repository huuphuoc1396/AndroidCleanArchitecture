package com.example.clean_architecture.core.platform

import androidx.viewbinding.ViewBinding

abstract class BaseBindingViewHolder<Item, VB : ViewBinding>(
    val viewBinding: VB,
    onItemClickListener: (Item) -> Unit,
) : BaseViewHolder<Item>(viewBinding.root, onItemClickListener)