package com.example.myselfcustom

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB: ViewBinding> : Fragment() {

    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = createViewBinding()
        if (!::binding.isInitialized) {
            Log.d(TAG, ERROR_INFO)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    abstract fun createViewBinding(): VB

    companion object {
        const val TAG = "BaseFragment"
        const val ERROR_INFO = "BaseFragment not Initialized"
    }

}