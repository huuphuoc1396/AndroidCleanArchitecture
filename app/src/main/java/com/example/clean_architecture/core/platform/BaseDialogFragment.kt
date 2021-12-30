package com.example.clean_architecture.core.platform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.example.clean_architecture.core.livedata.autoCleared

abstract class BaseDialogFragment<VB : ViewDataBinding> : DialogFragment() {

    abstract fun onCreateViewDataBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    open fun onBindVariable() {}

    var viewDataBinding: VB by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = onCreateViewDataBinding(inflater, container)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBindVariable()
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        viewDataBinding.executePendingBindings()
    }
}
