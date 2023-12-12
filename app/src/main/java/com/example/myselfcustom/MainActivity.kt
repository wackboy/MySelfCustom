package com.example.myselfcustom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myselfcustom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button1.setOnClickListener {
            binding.likeView.minus()
        }
        binding.button2.setOnClickListener {
            binding.likeView.plus()
        }
    }
}