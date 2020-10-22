package com.example.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.common.livedata.autoCleared
import com.example.common.viewmodel.NavigationSharedViewModel
import com.example.lib.exception.ApiException
import com.example.lib.exception.CoroutineException
import com.example.presentation.R
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

abstract class BaseFragment<V : ViewDataBinding> : Fragment() {

    private var networkErrorDialog: AlertDialog? = null

    private val navigationSharedViewModel: NavigationSharedViewModel by sharedViewModel()

    @get:LayoutRes
    abstract val layoutResId: Int

    var viewDataBinding: V by autoCleared()

    open fun getViewModel(): BaseViewModel? {
        return null
    }

    open fun setBindingVariable() {}

    open fun onBackPressed(): Boolean {
        return true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBindingVariable()
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        viewDataBinding.executePendingBindings()

        getViewModel()?.networkError?.observe(viewLifecycleOwner, Observer { coroutineException ->
            handleNetworkError(coroutineException)
        })

        navigationSharedViewModel.backNavigation.observe(viewLifecycleOwner, Observer {
            if (onBackPressed()) {
                activity?.finishAfterTransition()
            }
        })
    }

    private fun handleNetworkError(coroutineException: CoroutineException) {
        when (coroutineException) {
            is ApiException.ConnectionException -> {
                showNetworkErrorDialog(getString(R.string.no_internet_error))
            }

            is ApiException.ServerException -> {
                val errorMessage = coroutineException.errorMessage
                if (errorMessage.isNotEmpty()) {
                    showNetworkErrorDialog(errorMessage)
                } else {
                    showNetworkErrorDialog(getString(R.string.unexpected_error))
                }
            }

            is ApiException.UnknownException -> {
                showNetworkErrorDialog(getString(R.string.unexpected_error))
            }
        }
    }

    private fun showNetworkErrorDialog(message: String) {
        networkErrorDialog?.dismiss()
        networkErrorDialog = AlertDialog.Builder(requireContext())
            .setTitle(R.string.network_error)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(R.string.ok) { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            .create()
        networkErrorDialog?.show()
    }

    override fun onDestroy() {
        networkErrorDialog?.dismiss()
        super.onDestroy()
    }
}
