package com.example.clean_architecture.core_android.extension

import android.view.View
import androidx.databinding.BindingAdapter
import com.example.clean_architecture.core_android.listener.OnSingleClickListener

@BindingAdapter("onSingleClick")
fun View.setOnSingleClickListener(onClickListener: View.OnClickListener?) {
    onClickListener?.also {
        setOnClickListener(OnSingleClickListener(it))
    } ?: setOnClickListener(null)
}

fun View.setOnSingleClickListener(onClickListener: (View) -> Unit) {
    setOnSingleClickListener(View.OnClickListener { view ->
        onClickListener(view)
    })
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}