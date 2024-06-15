package com.example.myselfcustom.sourcecodelearn.apt

import android.os.Bundle
import com.example.myselfcustom.BaseActivity
import com.example.myselfcustom.databinding.ActivityAptBinding

class AptActivity : BaseActivity<ActivityAptBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun createViewBinding(): ActivityAptBinding {
        return ActivityAptBinding.inflate(layoutInflater)
    }
}