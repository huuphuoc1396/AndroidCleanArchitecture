package com.example.clean_architecture.presentation

import android.os.Bundle
import com.example.clean_architecture.core.platform.BaseActivity
import com.example.clean_architecture.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var viewDataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewDataBinding.root)
    }
}