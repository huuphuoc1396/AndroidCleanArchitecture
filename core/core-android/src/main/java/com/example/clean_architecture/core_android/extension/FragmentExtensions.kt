package com.example.clean_architecture.core_android.extension

import androidx.fragment.app.Fragment
import com.example.clean_architecture.core_android.dialog.NetworkErrorDialogFragment
import com.example.clean_architecture.core_lib.exception.ApiException
import com.example.clean_architecture.core_lib.exception.CoroutineException
import com.example.common_android.R

fun Fragment.handleNetworkError(coroutineException: CoroutineException) {
    val currentActivity = activity
    if (currentActivity != null && !currentActivity.isFinishing) {
        val message = when (coroutineException) {
            is ApiException.ConnectionException -> {
                getString(R.string.no_internet_error)
            }
            is ApiException.ServerException -> {
                val errorMessage = coroutineException.errorMessage
                if (errorMessage.isNotEmpty()) {
                    errorMessage
                } else {
                    getString(R.string.unexpected_error)
                }
            }
            is ApiException.UnknownException -> {
                getString(R.string.unexpected_error)
            }
            else -> ""
        }
        val dialogFragment = NetworkErrorDialogFragment.newInstance(message)
        dialogFragment.show(childFragmentManager, NetworkErrorDialogFragment::class.simpleName)
    }
}