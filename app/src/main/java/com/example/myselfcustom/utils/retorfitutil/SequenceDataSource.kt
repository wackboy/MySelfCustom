package com.example.myselfcustom.utils.retorfitutil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope

open class SequenceDataSource<T>(scope: CoroutineScope) : ScopeDataSource(scope) {

    val mediatorLiveData = MediatorLiveData<T>()
    private val requestLiveData = MutableLiveData<LiveData<T>>()


    init {
        mediatorLiveData.addSource(requestLiveData) { newLiveData ->
            mediatorLiveData.removeSource(requestLiveData)
            mediatorLiveData.addSource(newLiveData) { result ->
                mediatorLiveData.value = result
            }
        }
    }

    fun loadSequence(block: () -> LiveData<T>) {
        val liveData = block.invoke()
        requestLiveData.value = liveData
    }


}