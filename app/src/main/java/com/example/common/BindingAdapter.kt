package com.example.common

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun AppCompatImageView.setImageUrl(url: String){
    Glide.with(context)
        .load(url)
        .into(this)
}