package com.example.myselfcustom.viewscalerv

import android.os.Bundle
import com.example.myselfcustom.base.BaseActivity
import com.example.myselfcustom.databinding.ActivitySecondBinding

class SecondActivity : BaseActivity<ActivitySecondBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UIScaleRVHelper(this, binding.rvContent)
    }

    override fun createViewBinding() = ActivitySecondBinding.inflate(layoutInflater)

}









