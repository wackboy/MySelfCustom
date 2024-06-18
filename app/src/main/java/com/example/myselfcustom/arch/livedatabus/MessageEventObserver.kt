package com.example.myselfcustom.arch.livedatabus

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface MessageEventObserver<T> {

    fun post(value: T)

    fun broadcast(context: Context, value: T)

    fun observe(owner: LifecycleOwner, observer: Observer<T>)

    fun observeSticky(owner: LifecycleOwner, observer: Observer<T>)

    fun observeForever(observer: Observer<T>)

    fun observeForeverSticky(observer: Observer<T>)

    fun removeObserver(observer: Observer<T>)

}
