package com.example.clean_architecture.presentation.feature.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.clean_architecture.R
import com.example.clean_architecture.common.base.BaseFragment
import com.example.clean_architecture.common.base.BaseViewModel
import com.example.clean_architecture.databinding.FragmentDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private val detailViewModel: DetailViewModel by viewModel()

    override val layoutResId: Int = R.layout.fragment_detail

    override fun getViewModel(): BaseViewModel? {
        return detailViewModel
    }

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