package com.example.myselfcustom.ui.customview.viewscalerv

import android.os.Bundle
import com.example.myselfcustom.BaseFragment
import com.example.myselfcustom.databinding.ActivitySecondBinding

class SecondFragment : BaseFragment<ActivitySecondBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UIScaleRVHelper(this.context!!, binding.rvContent)
    }

    override fun createViewBinding() = ActivitySecondBinding.inflate(layoutInflater)

}









