package com.example.myselfcustom.ui.customview.viewlike

import android.os.Bundle
import android.view.View
import com.example.myselfcustom.R
import com.example.myselfcustom.BaseActivity
import com.example.myselfcustom.databinding.ActivityThirdBinding

class ThirdActivity : BaseActivity<ActivityThirdBinding>() {

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