package com.example.clean_architecture.presentation

import android.os.Bundle
import com.example.clean_architecture.core_android.base.BaseActivity
import com.example.clean_architecture.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var viewDataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewDataBinding.root)
    }
}