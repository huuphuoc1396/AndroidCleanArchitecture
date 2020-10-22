package com.example.clean_architecture.presentation

import android.os.Bundle
import com.example.clean_architecture.R
import com.example.clean_architecture.common.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}