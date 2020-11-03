package com.example.clean_architecture.presentation.feature.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.clean_architecture.R
import com.example.clean_architecture.common.base.BaseFragment
import com.example.clean_architecture.common.base.BaseViewModel
import com.example.clean_architecture.databinding.FragmentDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private val viewModel: DetailViewModel by viewModel()

    private val args: DetailFragmentArgs by navArgs()

    override val layoutResId: Int = R.layout.fragment_detail

    override fun setBindingVariable() {
        super.setBindingVariable()
        viewDataBinding.viewModel = viewModel
    }

    override fun getViewModel(): BaseViewModel? {
        return viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        viewModel.setDetailItem(
            repoName = args.repoName,
            ownerLogin = args.ownerLogin
        )

        viewDataBinding.toolBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}