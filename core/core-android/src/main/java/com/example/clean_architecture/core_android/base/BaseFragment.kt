package com.example.clean_architecture.core_android.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.Size
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.clean_architecture.core_android.extension.handleNetworkError
import com.example.clean_architecture.core_android.livedata.autoCleared
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import timber.log.Timber

abstract class BaseFragment<V : ViewDataBinding> : Fragment(), EasyPermissions.PermissionCallbacks {

    abstract fun createViewDataBinding(inflater: LayoutInflater, container: ViewGroup?): V

    var viewDataBinding: V by autoCleared()

    open fun getViewModel(): BaseViewModel? {
        return null
    }

    open fun setBindingVariable() {}

    open fun onBackPressed(): Boolean {
        return true
    }

    open fun onReturnedFromAppSettings() {}

    fun hasPermission(@Size(min = 1) vararg permissions: String): Boolean {
        return EasyPermissions.hasPermissions(requireContext(), *permissions)
    }

    fun requestPermission(
        rationale: String,
        requestCode: Int = PERMISSION_REQUEST_CODE,
        @Size(min = 1) vararg permissions: String
    ) {
        EasyPermissions.requestPermissions(this, rationale, requestCode, *permissions)
    }

    override fun onAttach(context: Context) {
        Timber.tag(LIFECYCLE_TAG).i("${this::class.simpleName} onAttach")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.tag(LIFECYCLE_TAG).i("${this::class.simpleName} onCreate")
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                isEnabled = !onBackPressed()
                if (!isEnabled) {
                    activity?.onBackPressed()
                }
            }
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

        getViewModel()?.networkError?.observe(viewLifecycleOwner, { coroutineError ->
            handleNetworkError(coroutineError)
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            Timber.i("${this::class.simpleName} returned from app settings screen")
            onReturnedFromAppSettings()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Timber.tag(LIFECYCLE_TAG).i("${this::class.simpleName} onActivityCreated")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Timber.tag(LIFECYCLE_TAG).i("${this::class.simpleName} onStart")
        super.onStart()
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

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {}

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {}

    companion object {
        private const val LIFECYCLE_TAG = "FragmentLifecycle"
        private const val PERMISSION_REQUEST_CODE: Int = Activity.RESULT_FIRST_USER + 1
    }
}
