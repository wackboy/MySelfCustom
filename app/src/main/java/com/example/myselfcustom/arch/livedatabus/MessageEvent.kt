package com.example.myselfcustom.arch.livedatabus

import android.content.Context
import android.os.Looper
import androidx.annotation.MainThread
import androidx.lifecycle.ExternalNewLiveData
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
class MessageEvent<T>(val key: String) : MessageEventObserver<T> {

    private val liveData by lazy { ExternalNewLiveData<T>(key) }

    override fun post(value: T) {
        postInternal(value)
    }

    override fun broadcast(context: Context, value: T) {
        GlobalScope.launch(Dispatchers.Main) {
            broadcastInternal(context.applicationContext, value)
        }
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
        GlobalScope.launch(Dispatchers.Main) {
            observeInternal(owner, observer)
        }
    }

    override fun observeSticky(owner: LifecycleOwner, observer: Observer<T>) {
        GlobalScope.launch(Dispatchers.Main) {
            observeStickyInternal(owner, observer)
        }
    }

    override fun observeForever(observer: Observer<T>) {
        GlobalScope.launch(Dispatchers.Main) {
            observeForeverInternal(observer)
        }
    }

    override fun observeForeverSticky(observer: Observer<T>) {
        GlobalScope.launch(Dispatchers.Main) {
            observeStickyForeverInternal(observer)
        }
    }

    override fun removeObserver(observer: Observer<T>) {
        GlobalScope.launch(Dispatchers.Main) {
            removeObserverInternal(observer)
        }
    }

    private fun broadcastInternal(context: Context, value: T) {
        TODO()
    }

    private fun postInternal(value: T) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            liveData.value = value
        } else {
            liveData.postValue(value)
        }
    }

    @MainThread
    private fun observeInternal(owner: LifecycleOwner, observer: Observer<T>) {
        liveData.observe(owner, observer)
    }

    @MainThread
    private fun observeStickyInternal(owner: LifecycleOwner, observer: Observer<T>) {
        liveData.observeSticky(owner, observer)
    }

    @MainThread
    private fun observeForeverInternal(observer: Observer<T>) {
        liveData.observeForever(observer)
    }

    @MainThread
    private fun observeStickyForeverInternal(observer: Observer<T>) {
        liveData.observeStickyForever(observer)
    }

    @MainThread
    private fun removeObserverInternal(observer: Observer<T>) {
        liveData.removeObserver(observer)
    }

}