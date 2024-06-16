package com.example.myselfcustom.sourcecodelearn.sourceeventbus

import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.myselfcustom.BaseActivity
import com.example.myselfcustom.databinding.ActivityEventBusBinding
import com.example.myselfcustom.ui.slideleftfragment.SlideLeftCommon
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

@Route(path = SlideLeftCommon.EVENTBUS_ACTIVITY_PATH)
class EventBusActivity : BaseActivity<ActivityEventBusBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.jumpToAnother.setOnClickListener {
            startActivity(Intent(this, EventPostActivity::class.java))
        }
        binding.subscribe.setOnClickListener {
            EventBus.getDefault().register(this)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun handleMessage(messageEvent: MessageEvent) {
        binding.tvShowEvent.text = messageEvent.message
    }

    // 测试粘性事件
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun handleStickMessage(messageEvent: MessageEvent) {
        binding.showStickEvent.text = messageEvent.message
    }


    override fun createViewBinding() =
        ActivityEventBusBinding.inflate(layoutInflater)

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

}