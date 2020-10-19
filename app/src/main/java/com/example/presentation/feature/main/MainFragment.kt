package com.example.presentation.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.common.BaseFragment
import com.example.presentation.databinding.FragmentMainBinding
import com.example.presentation.model.RepoItem
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment() {

    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerRepoItems.adapter = MainListAdapter()

        viewModel.searchRepos("android")
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