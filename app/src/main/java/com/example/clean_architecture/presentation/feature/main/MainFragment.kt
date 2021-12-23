package com.example.clean_architecture.presentation.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.clean_architecture.R
import com.example.clean_architecture.core.extension.dismissKeyboard
import com.example.clean_architecture.core.livedata.autoCleared
import com.example.clean_architecture.core.platform.BaseFragment
import com.example.clean_architecture.core.platform.BaseViewModel
import com.example.clean_architecture.databinding.FragmentMainBinding
import com.example.clean_architecture.presentation.feature.main.extension.setOnSearchAction
import com.example.clean_architecture.presentation.feature.main.list.MainListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel: MainViewModel by viewModels()

    private var mainListAdapter by autoCleared<MainListAdapter>()

    private var doubleBackToExit = false

    override fun createViewDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun setBindingVariable() {
        super.setBindingVariable()
        viewDataBinding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observe()
    }

    private fun initViews() = with(viewDataBinding) {
        mainListAdapter = MainListAdapter(
            onItemClickListener = { repoItem ->
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToDetailFragment(
                        repoName = repoItem.name,
                        ownerLogin = repoItem.owner.login,
                    )
                )
            }
        )
        recyclerRepoItems.adapter = mainListAdapter

        editQuery.setOnSearchAction { view, query ->
            search(view, query)
        }
    }

    private fun observe() = with(viewModel) {
        repoItems.observe(viewLifecycleOwner) {
            mainListAdapter.submitList(it)
        }
    }

    private fun search(view: View, text: String) {
        activity?.dismissKeyboard(view.windowToken)
        viewModel.searchRepos(text)
    }

    override fun onBackPressed(): Boolean {
        if (!doubleBackToExit) {
            doubleBackToExit = true
            toast(R.string.msg_double_back_to_exit)
            return false
        }
        return true
    }
}