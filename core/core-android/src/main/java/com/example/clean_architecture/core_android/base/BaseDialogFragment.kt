package com.example.clean_architecture.core_android.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.example.clean_architecture.core_android.livedata.autoCleared

abstract class BaseDialogFragment<V : ViewDataBinding> : DialogFragment() {

    @get:LayoutRes
    protected open val layoutResId: Int = -1

    protected var viewDataBinding: V by autoCleared()

    open fun getViewModel(): BaseViewModel? {
        return null
    }

    open fun setBindingVariable() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (layoutResId != -1) {
            viewDataBinding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
            return viewDataBinding.root
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (layoutResId != -1) {
            setBindingVariable()
            viewDataBinding.lifecycleOwner = viewLifecycleOwner
            viewDataBinding.executePendingBindings()
        }
    }
}
