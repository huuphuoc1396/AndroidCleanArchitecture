package com.example.common.viewmodel

import androidx.lifecycle.ViewModel
import com.example.common.livedata.SingleLiveData

class NavigationSharedViewModel : ViewModel() {

    val backNavigation = SingleLiveData<Unit>()
}