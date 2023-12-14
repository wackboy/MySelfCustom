package com.example.myselfcustom

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myselfcustom.databinding.ActivityMainBinding
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var ratio = 0f
    private var reversed = false

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
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button1.setOnClickListener(clicker)
        binding.button2.setOnClickListener(clicker)
        moveSlideBar()
    }

    private fun moveSlideBar() {
        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                if (ratio >= 1) reversed = true
                if (ratio <= 0) reversed = false
                ratio += if (reversed) -0.1f else 0.1f
                runOnUiThread {
                    binding.indicator.setBendingRatio(ratio)
                }
            }
        }, 800, 60)
    }

}