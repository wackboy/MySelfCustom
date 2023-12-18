package com.example.myselfcustom.viewrect

import android.os.Bundle
import com.example.myselfcustom.base.BaseActivity
import com.example.myselfcustom.databinding.ActivityFifthBinding

class FifthActivity : BaseActivity<ActivityFifthBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun createViewBinding() = ActivityFifthBinding.inflate(layoutInflater)
}