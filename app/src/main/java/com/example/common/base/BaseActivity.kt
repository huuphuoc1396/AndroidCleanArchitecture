package com.example.common.base

import androidx.appcompat.app.AppCompatActivity
import com.example.common.viewmodel.NavigationSharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class BaseActivity : AppCompatActivity() {

    private val navigationSharedViewModel: NavigationSharedViewModel by viewModel()

    override fun onBackPressed() {
        navigationSharedViewModel.backNavigation.call()
    }
}