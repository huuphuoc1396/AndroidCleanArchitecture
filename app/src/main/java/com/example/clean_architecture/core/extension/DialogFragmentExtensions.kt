package com.example.clean_architecture.core.extension

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

fun DialogFragment.showIfNotExist(fragmentManager: FragmentManager, tag: String?) {
    val isNotExist = fragmentManager.findFragmentByTag(tag) == null
    if (isNotExist) {
        show(fragmentManager, tag)
    }
}

fun DialogFragment.dismissIfAdded() {
    if (isAdded) dismiss()
}