package com.example.clean_architecture.presentation.feature.main

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.BindingAdapter
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.clean_architecture.core_android.base.BaseFragment
import com.example.clean_architecture.core_android.base.BaseViewModel
import com.example.clean_architecture.core_android.extension.dismissKeyboard
import com.example.clean_architecture.databinding.FragmentMainBinding
import com.example.clean_architecture.presentation.model.RepoItem
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel: MainViewModel by viewModel()

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
    }

    private fun initViews() {
        viewDataBinding.recyclerRepoItems.adapter = MainListAdapter(
            onItemClickListener = { repoItem ->
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToDetailFragment(
                        repoName = repoItem.name,
                        ownerLogin = repoItem.owner.login
                    )
                )
            }
        )
        viewDataBinding.editQuery.setOnEditorActionListener { view: View, actionId: Int, _: KeyEvent? ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                search(view)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
        viewDataBinding.editQuery.setOnKeyListener { view: View, keyCode: Int, event: KeyEvent ->
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

    object Binding {

        @JvmStatic
        @BindingAdapter("repoItems")
        fun setRepoItems(recyclerView: RecyclerView, repoItem: List<RepoItem>) {
            val adapter = recyclerView.adapter as? MainListAdapter
            adapter?.submitList(repoItem)
        }
    }
}