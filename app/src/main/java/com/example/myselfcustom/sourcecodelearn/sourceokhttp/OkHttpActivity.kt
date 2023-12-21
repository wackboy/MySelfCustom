package com.example.myselfcustom.sourcecodelearn.sourceokhttp

import android.os.Bundle
import com.example.myselfcustom.base.BaseActivity
import com.example.myselfcustom.databinding.ActivityOkHttpBinding

class OkHttpActivity : BaseActivity<ActivityOkHttpBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun createViewBinding() =
        ActivityOkHttpBinding.inflate(layoutInflater)

}