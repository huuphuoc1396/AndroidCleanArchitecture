package com.example.clean_architecture.presentation.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.clean_architecture.R
import com.example.clean_architecture.core.extension.dismissKeyboard
import com.example.clean_architecture.core.livedata.autoCleared
import com.example.clean_architecture.core.platform.BaseFragment
import com.example.clean_architecture.databinding.FragmentMainBinding
import com.example.clean_architecture.presentation.feature.main.extension.setOnSearchAction
import com.example.clean_architecture.presentation.feature.main.list.MainListAdapter
import com.example.clean_architecture.presentation.feature.main.model.RepoItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel by viewModels()

    private var mainListAdapter by autoCleared<MainListAdapter>()

    private var isDoubleBackToExit = false

    override fun onCreateViewDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeOnFragment()
    }

    override fun onBindVariable() {
        super.onBindVariable()
        viewDataBinding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeOnView()
    }

    private fun initView() {
        setupListView()
        setupQueryInput()
    }

    private fun setupQueryInput() = with(viewDataBinding) {
        editQuery.setOnSearchAction { view, query ->
            search(view, query)
        }
    }

    private fun setupListView() = with(viewDataBinding) {
        mainListAdapter = MainListAdapter(
            onItemClickListener = { repoItem ->
                navigateToDetail(repoItem)
            }
        )
        recyclerRepoItems.adapter = mainListAdapter
    }

    private fun navigateToDetail(repoItem: RepoItem) {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToDetailFragment(
                repoName = repoItem.name,
                ownerLogin = repoItem.owner.login,
            )
        )
    }

    private fun observeOnFragment() {
        viewModel.firstRunChecking.observe(this@MainFragment) { isFirstRun ->
            if (isFirstRun) {
                toast(R.string.msg_first_run)
                viewModel.setFistRun()
            }
        }
    }

    private fun observeOnView() = with(viewModel) {
        repoItems.observe(viewLifecycleOwner) {
            mainListAdapter.submitList(it)
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