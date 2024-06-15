package com.example.myselfcustom.ui.customview.viewslidecard

import android.os.Bundle
import com.example.myselfcustom.BaseFragment
import com.example.myselfcustom.databinding.ActivityFourthBinding

class FourthFragment : BaseFragment<ActivityFourthBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UISlideCardHelper.initHelper(this.context!!, binding.cardRv)
    }

    override fun createViewBinding() = ActivityFourthBinding.inflate(layoutInflater)

}