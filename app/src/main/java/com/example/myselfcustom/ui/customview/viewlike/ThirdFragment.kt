package com.example.myselfcustom.ui.customview.viewlike

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.myselfcustom.BaseFragment
import com.example.myselfcustom.R
import com.example.myselfcustom.databinding.ActivityThirdBinding
import com.example.myselfcustom.ui.slideleftfragment.SlideLeftCommon

@Route(path = SlideLeftCommon.THIRD_FRAGMENT_PATH)
class ThirdFragment : BaseFragment<ActivityThirdBinding>() {

    private val clicker = View.OnClickListener {
        when (it.id) {
            R.id.button1 -> {
                binding.likeView.minus()
            }
            R.id.button2 -> {
                binding.likeView.plus()
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.button1.setOnClickListener(clicker)
        binding.button2.setOnClickListener(clicker)
    }

    override fun createViewBinding() = ActivityThirdBinding.inflate(layoutInflater)
}