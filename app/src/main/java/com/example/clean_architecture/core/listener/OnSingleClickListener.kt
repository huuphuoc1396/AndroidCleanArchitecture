package com.example.clean_architecture.core.listener

import android.view.View
import java.util.concurrent.atomic.AtomicBoolean

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