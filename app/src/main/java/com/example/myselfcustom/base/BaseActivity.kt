package com.example.myselfcustom.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB: ViewBinding> : AppCompatActivity() {
    companion object {
        const val TAG = "BaseActivity"
        const val ERROR_INFO = "BaseActivity Not Initialized"
    }

    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = createViewBinding()
        if (!::binding.isInitialized) {
            // 兜底
            Log.d(TAG, ERROR_INFO)
        } else {
            setContentView(binding.root)
        }
    }

    abstract fun createViewBinding(): VB

}