package com.example.clean_architecture.presentation.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.clean_architecture.R
import com.example.clean_architecture.core.extension.dismissKeyboard
import com.example.clean_architecture.core.platform.BaseFragment
import com.example.clean_architecture.databinding.FragmentMainBinding
import com.example.clean_architecture.presentation.feature.main.model.RepoItem
import com.example.clean_architecture.presentation.feature.main.ui.RepoList
import com.example.clean_architecture.presentation.feature.main.ui.SearchInput
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
                    MainScreen()
                }
            }
        }
    }

    @Composable
    private fun MainScreen() {
        Column {
            var query: String by remember { mutableStateOf("") }
            SearchInput(
                text = query,
                label = stringResource(id = R.string.msg_type_your_query),
                onValueChanged = { text -> query = text },
                onActionSearch = { search(requireView(), query) },
                modifier = Modifier.fillMaxWidth()
            )
            val repoList: List<RepoItem> by viewModel.repoItems.observeAsState(listOf())
            RepoList(
                repoList,
                onItemClick = { repoItem ->
                    navigateToDetail(repoItem)
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }

    override fun onBindVariable() {
        super.onBindVariable()
        viewDataBinding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
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