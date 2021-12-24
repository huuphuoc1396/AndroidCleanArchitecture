package com.example.clean_architecture.core.platform

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.clean_architecture.R
import com.example.clean_architecture.core.extension.available
import com.example.clean_architecture.core.extension.dismissIfAdded
import com.example.clean_architecture.core.extension.isAvailable
import com.example.clean_architecture.core.extension.showIfNotExist
import com.example.clean_architecture.core.livedata.autoCleared
import com.example.clean_architecture.domain.core.error.ApiFailure
import com.example.clean_architecture.domain.core.error.DefaultFailure
import com.example.clean_architecture.domain.core.error.Failure
import com.example.clean_architecture.presentation.dialog.LoadingDialogFragment
import com.example.clean_architecture.presentation.dialog.NetworkErrorDialogFragment
import timber.log.Timber

abstract class BaseFragment<V : ViewDataBinding> : Fragment() {

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (onBackPressed()) {
                isEnabled = false
                activity?.onBackPressed()
            }
        }
    }

    private var loadingDialogFragment: LoadingDialogFragment? = null

    var viewDataBinding: V by autoCleared()

    abstract fun createViewDataBinding(inflater: LayoutInflater, container: ViewGroup?): V

    open fun getViewModel(): BaseViewModel? = null

    open fun setBindingVariable() = Unit

    open fun onBackPressed(): Boolean = true

    override fun onAttach(context: Context) {
        Timber.tag(LIFECYCLE_TAG).i("${this::class.simpleName} onAttach")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.tag(LIFECYCLE_TAG).i("${this::class.simpleName} onCreate")
        super.onCreate(savedInstanceState)

        activity?.onBackPressedDispatcher?.addCallback(this, onBackPressedCallback)

        getViewModel()?.failure?.observe(this, { coroutineError ->
            showNetworkError(coroutineError)
        })

        getViewModel()?.isLoading?.observe(this, { isLoading ->
            showLoading(isLoading)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.tag(LIFECYCLE_TAG).i("${this::class.simpleName} onCreateView")
        viewDataBinding = createViewDataBinding(inflater, container)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.tag(LIFECYCLE_TAG).i("${this::class.simpleName} onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        setBindingVariable()
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        viewDataBinding.executePendingBindings()
    }

    override fun onStart() {
        Timber.tag(LIFECYCLE_TAG).i("${this::class.simpleName} onStart")
        super.onStart()

        onBackPressedCallback.isEnabled = true
    }

    override fun onResume() {
        Timber.tag(LIFECYCLE_TAG).i("${this::class.simpleName} onResume")
        super.onResume()
    }

    override fun onPause() {
        Timber.tag(LIFECYCLE_TAG).i("${this::class.simpleName} onPause")
        super.onPause()
    }

    override fun onStop() {
        Timber.tag(LIFECYCLE_TAG).i("${this::class.simpleName} onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Timber.tag(LIFECYCLE_TAG).i("${this::class.simpleName} onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Timber.tag(LIFECYCLE_TAG).i("${this::class.simpleName} onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Timber.tag(LIFECYCLE_TAG).i("${this::class.simpleName} onDetach")
        super.onDetach()
    }

    open fun showNetworkError(failure: Failure) {
        val message = when (failure) {
            is ApiFailure.Connection -> {
                getString(R.string.msg_no_internet_error)
            }
            is ApiFailure.Server -> {
                val errorMessage = failure.errorMessage
                if (errorMessage.isNotEmpty()) {
                    errorMessage
                } else {
                    getString(R.string.msg_unexpected_error)
                }
            }
            is ApiFailure.Unknown -> {
                getString(R.string.msg_unexpected_error)
            }
            else -> ""
        }
        activity.available {
            val dialogFragment = NetworkErrorDialogFragment.newInstance(message)
            dialogFragment.showIfNotExist(childFragmentManager, NetworkErrorDialogFragment.TAG)
        }
    }

    open fun showLoading(isLoading: Boolean) {
        if (isLoading && activity.isAvailable()) {
            loadingDialogFragment = LoadingDialogFragment.newInstance()
            loadingDialogFragment?.showIfNotExist(childFragmentManager, LoadingDialogFragment.TAG)
        } else {
            loadingDialogFragment?.dismissIfAdded()
            loadingDialogFragment = null
        }
    }

    open fun toast(message: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, length).show()
    }

    open fun toast(resId: Int, length: Int = Toast.LENGTH_SHORT) {
        toast(getString(resId), length)
    }

    companion object {
        private const val LIFECYCLE_TAG = "FragmentLifecycle"
    }
}