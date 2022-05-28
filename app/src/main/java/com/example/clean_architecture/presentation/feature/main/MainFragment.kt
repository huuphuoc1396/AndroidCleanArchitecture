package com.example.clean_architecture.presentation.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.clean_architecture.R
import com.example.clean_architecture.core.extension.dismissKeyboard
import com.example.clean_architecture.core.platform.BaseFragment
import com.example.clean_architecture.databinding.FragmentMainBinding
import com.example.clean_architecture.presentation.feature.main.extension.setOnSearchAction
import com.example.clean_architecture.presentation.feature.main.list.RepoList
import com.example.clean_architecture.presentation.feature.main.model.RepoItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel by viewModels()

    private var isDoubleBackToExit = false

    override fun onCreateViewDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false).apply {
            composeView.setContent {
                MaterialTheme {
                    RepoList(mainViewModel = viewModel!!, onItemClick = { repoItem ->
                        navigateToDetail(repoItem)
                    })
                }
            }
        }
    }

    override fun onBindVariable() {
        super.onBindVariable()
        viewDataBinding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() = with(viewDataBinding) {
        editQuery.setOnSearchAction { view, query ->
            search(view, query)
        }
    }

    private fun navigateToDetail(repoItem: RepoItem) {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToDetailFragment(
                repoName = repoItem.name,
                ownerLogin = repoItem.owner.login,
            )
        )
    }

    private fun initViewModel() = with(viewModel) {
        firstRunChecking.observe(viewLifecycleOwner) { isFirstRun ->
            if (isFirstRun) {
                toast(R.string.msg_first_run)
                setFistRun()
            }
        }
    }

    private fun search(view: View, text: String) {
        activity?.dismissKeyboard(view.windowToken)
        viewModel.searchRepos(text)
    }

    override fun onBackPressed(): Boolean {
        if (!isDoubleBackToExit) {
            toast(R.string.msg_double_back_to_exit)
            isDoubleBackToExit = true
            lifecycleScope.launch {
                delay(DELAY_TO_RESET_DOUBLE_BACK)
                isDoubleBackToExit = false
            }
            return false
        }
        isDoubleBackToExit = false
        return true
    }

    companion object {

        private const val DELAY_TO_RESET_DOUBLE_BACK = 1500L
    }
}