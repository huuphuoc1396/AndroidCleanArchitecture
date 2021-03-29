package com.example.clean_architecture.presentation.feature.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.clean_architecture.core_android.base.BaseFragment
import com.example.clean_architecture.core_android.base.BaseViewModel
import com.example.clean_architecture.presentation.databinding.FragmentDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private val viewModel: DetailViewModel by viewModel()

    private val args: DetailFragmentArgs by navArgs()

    override fun createViewDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater, container, false)
    }

    override fun setBindingVariable() {
        super.setBindingVariable()
        viewDataBinding.viewModel = viewModel
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        viewModel.init(
            repoName = args.repoName,
            ownerLogin = args.ownerLogin
        )

        viewDataBinding.toolBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}