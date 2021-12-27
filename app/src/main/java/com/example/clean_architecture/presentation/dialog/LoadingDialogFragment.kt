package com.example.clean_architecture.presentation.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.clean_architecture.R
import com.example.clean_architecture.core.platform.BaseFullScreenDialogFragment
import com.example.clean_architecture.databinding.DialogLoadingBinding

class LoadingDialogFragment : BaseFullScreenDialogFragment<DialogLoadingBinding>() {

    override fun createViewDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = DialogLoadingBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme_Dialog_Loading)
        isCancelable = false
    }

    companion object {
        const val TAG = "LoadingDialogFragment"

        fun newInstance() = LoadingDialogFragment()
    }
}