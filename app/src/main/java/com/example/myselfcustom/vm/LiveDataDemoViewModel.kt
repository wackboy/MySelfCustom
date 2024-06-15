package com.example.myselfcustom.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveDataDemoViewModel : ViewModel() {

    private val _processLiveData = MutableLiveData(0)
    val processLiveData: LiveData<Int> = _processLiveData

}