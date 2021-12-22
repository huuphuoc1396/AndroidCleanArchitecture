package com.example.clean_architecture.presentation.feature.main

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.clean_architecture.core.extension.dismissKeyboard
import com.example.clean_architecture.core.livedata.autoCleared
import com.example.clean_architecture.core.platform.BaseFragment
import com.example.clean_architecture.core.platform.BaseViewModel
import com.example.clean_architecture.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel: MainViewModel by viewModels()

    private var mainListAdapter by autoCleared<MainListAdapter>()

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observe()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun observe() = with(viewModel) {
        repoItems.observe(this@MainFragment) {
            mainListAdapter.submitList(it)
        }
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
        editQuery.setOnEditorActionListener { view: View, actionId: Int, _: KeyEvent? ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                search(view)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
        editQuery.setOnKeyListener { view: View, keyCode: Int, event: KeyEvent ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                search(view)
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

    private fun search(view: View) {
        activity?.dismissKeyboard(view.windowToken)
        viewModel.searchRepos()
    }
}