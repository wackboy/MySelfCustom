package com.example.myselfcustom

import android.os.Bundle
import com.example.myselfcustom.arch.livedatabus.LiveDataBus
import com.example.myselfcustom.databinding.ActivityMainBinding
import com.example.myselfcustom.ui.slideleftfragment.SlideClickEvent

class MainActivity : BaseActivity<ActivityMainBinding>(), CommonClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LiveDataBus.get().of(SlideClickEvent::class.java).clickEvent().observe(this) {
            binding.drawerLayout.closeDrawers()
        }
//        EventBus.getDefault().register(this)
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    fun getSlideClickEvent(event: SlideClickEvent) {
//        binding.drawerLayout.closeDrawers()
//    }

    override fun createViewBinding() =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onItemClick(clazz: Class<out Any>) {
    }

    override fun onDestroy() {
        super.onDestroy()
//        EventBus.getDefault().unregister(this)
    }

}