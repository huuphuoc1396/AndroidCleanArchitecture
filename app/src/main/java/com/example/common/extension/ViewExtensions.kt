package com.example.common.extension

import android.view.View
import androidx.databinding.BindingAdapter
import java.util.concurrent.atomic.AtomicBoolean

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

class OnSingleClickListener(
    private val onClickListener: View.OnClickListener,
    private val intervalMs: Long = 1000L
) : View.OnClickListener {
    private val canClick = AtomicBoolean(true)

    override fun onClick(view: View?) {
        if (canClick.getAndSet(false)) {
            view?.run {
                postDelayed({
                    canClick.set(true)
                }, intervalMs)
                onClickListener.onClick(view)
            }
        }
    }
}