package com.example.myselfcustom

import android.os.Bundle
import com.example.myselfcustom.databinding.ActivityMainBinding
import com.example.myselfcustom.ui.slideleftfragment.SlideClickEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : BaseActivity<ActivityMainBinding>(), CommonClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun getSlideClickEvent(event: SlideClickEvent) {
        binding.drawerLayout.closeDrawers()
    }

    override fun createViewBinding() =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onItemClick(clazz: Class<out Any>) {
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

}