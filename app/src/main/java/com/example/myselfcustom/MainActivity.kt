package com.example.myselfcustom

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.myselfcustom.databinding.ActivityMainBinding
import com.example.myselfcustom.vm.LiveDataDemoViewModel

class MainActivity : BaseActivity<ActivityMainBinding>(), MainRvClickListener {

    private val vm by lazy {
        ViewModelProvider(this)[LiveDataDemoViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.slideLeftFg.setOnClickListener {
            binding.drawerLayout.closeDrawers()
        }
    }

    override fun createViewBinding() =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onItemClick(clazz: Class<out Any>) {
    }

}