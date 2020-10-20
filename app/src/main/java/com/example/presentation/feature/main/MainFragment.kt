package com.example.presentation.feature.main

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AlertDialog
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.common.base.BaseFragment
import com.example.common.extension.dismissKeyboard
import com.example.lib.exception.ApiException
import com.example.lib.exception.CoroutineException
import com.example.presentation.R
import com.example.presentation.databinding.FragmentMainBinding
import com.example.presentation.model.RepoItem
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment() {

    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainViewModel by viewModel()
    private var errorDialog: AlertDialog? = null

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
        initViews()
        observe()
    }

    private fun initViews() {
        binding.recyclerRepoItems.adapter = MainListAdapter()
        binding.editQuery.setOnEditorActionListener { view: View, actionId: Int, _: KeyEvent? ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                search(view)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
        binding.editQuery.setOnKeyListener { view: View, keyCode: Int, event: KeyEvent ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                search(view)
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

    private fun observe() {
        viewModel.error.observe(viewLifecycleOwner, Observer { coroutineException ->
            handleNetworkError(coroutineException)
        })
    }

    private fun search(view: View) {
        activity?.dismissKeyboard(view.windowToken)
        viewModel.searchRepos()
    }

    private fun handleNetworkError(coroutineException: CoroutineException) {
        when (coroutineException) {
            is ApiException.ConnectionException -> {
                showErrorDialog(getString(R.string.no_internet_error))
            }

            is ApiException.ServerException -> {
                val errorMessage = coroutineException.errorMessage
                if (errorMessage.isNotEmpty()) {
                    showErrorDialog(errorMessage)
                } else {
                    showErrorDialog(getString(R.string.unexpected_error))
                }
            }

            is ApiException.UnknownException -> {
                showErrorDialog(getString(R.string.unexpected_error))
            }
        }
    }

    private fun showErrorDialog(message: String) {
        errorDialog?.dismiss()
        errorDialog = AlertDialog.Builder(requireContext())
            .setTitle(R.string.network_error)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(R.string.ok) { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            .create()
        errorDialog?.show()
    }

    override fun onDestroy() {
        errorDialog?.dismiss()
        super.onDestroy()
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