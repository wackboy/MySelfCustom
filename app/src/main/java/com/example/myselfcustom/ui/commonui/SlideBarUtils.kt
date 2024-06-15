package com.example.myselfcustom.ui.commonui

import android.app.Activity
import android.content.Context
import androidx.viewbinding.ViewBinding
import java.util.Timer
import java.util.TimerTask

object SlideBarUtils {

    private var ratio = 0f
    private var reversed = false

    private fun moveSlideBar(context: Context, viewBinding: ViewBinding) {
        Timer().schedule(object : TimerTask() {
            override fun run() {
                if (ratio >= 1) reversed = true
                if (ratio <= 0) reversed = false
                ratio += if (reversed) -0.1f else 0.1f
                (context as Activity).runOnUiThread {
//                    viewBinding.indicator.setBendingRatio(ratio)
                }
            }
        }, 800, 60)
    }

}