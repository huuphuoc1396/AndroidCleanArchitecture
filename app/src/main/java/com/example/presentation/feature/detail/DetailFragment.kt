package com.example.presentation.feature.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.common.base.BaseFragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentDetailBinding

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    override val layoutResId: Int = R.layout.fragment_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        viewDataBinding.toolBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}