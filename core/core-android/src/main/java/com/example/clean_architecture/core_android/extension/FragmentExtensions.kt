package com.example.clean_architecture.core_android.extension

import androidx.fragment.app.Fragment
import com.example.clean_architecture.core_android.dialog.NetworkErrorDialogFragment
import com.example.clean_architecture.core_lib.error.ApiError
import com.example.clean_architecture.core_lib.error.CoroutineError
import com.example.common_android.R

fun Fragment.handleNetworkError(coroutineError: CoroutineError) {
    val currentActivity = activity
    if (currentActivity != null && !currentActivity.isFinishing) {
        val message = when (coroutineError) {
            is ApiError.ConnectionError -> {
                getString(R.string.no_internet_error)
            }
            is ApiError.ServerError -> {
                val errorMessage = coroutineError.errorMessage
                if (errorMessage.isNotEmpty()) {
                    errorMessage
                } else {
                    getString(R.string.unexpected_error)
                }
            }
            is ApiError.UnknownError -> {
                getString(R.string.unexpected_error)
            }
            else -> ""
        }
        val dialogFragment = NetworkErrorDialogFragment.newInstance(message)
        dialogFragment.show(childFragmentManager, NetworkErrorDialogFragment::class.simpleName)
    }
}