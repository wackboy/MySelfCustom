package com.example.myselfcustom.sourcecodelearn.sourcerxjava

import android.os.Bundle
import com.example.myselfcustom.base.BaseActivity
import com.example.myselfcustom.databinding.ActivityRxjavaLearnBinding

class RxjavaLearnActivity : BaseActivity<ActivityRxjavaLearnBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun createViewBinding(): ActivityRxjavaLearnBinding {
        return ActivityRxjavaLearnBinding.inflate(layoutInflater)
    }
}