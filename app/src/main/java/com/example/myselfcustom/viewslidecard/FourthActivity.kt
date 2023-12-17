package com.example.myselfcustom.viewslidecard

import android.os.Bundle
import com.example.myselfcustom.base.BaseActivity
import com.example.myselfcustom.databinding.ActivityFourthBinding

class FourthActivity : BaseActivity<ActivityFourthBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UISlideCardHelper.initHelper(this, binding.cardRv)
    }

    override fun createViewBinding() = ActivityFourthBinding.inflate(layoutInflater)

}