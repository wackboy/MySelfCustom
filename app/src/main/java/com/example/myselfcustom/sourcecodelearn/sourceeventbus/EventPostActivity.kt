package com.example.myselfcustom.sourcecodelearn.sourceeventbus

import android.os.Bundle
import com.example.myselfcustom.base.BaseActivity
import com.example.myselfcustom.databinding.ActivityEventPostBinding
import org.greenrobot.eventbus.EventBus

class EventPostActivity : BaseActivity<ActivityEventPostBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnPostEvent.setOnClickListener {
            EventBus.getDefault().postSticky(MessageEvent("这就是EventBus！！"))
            finish()
        }
    }

    override fun createViewBinding(): ActivityEventPostBinding {
        return ActivityEventPostBinding.inflate(layoutInflater)
    }

}