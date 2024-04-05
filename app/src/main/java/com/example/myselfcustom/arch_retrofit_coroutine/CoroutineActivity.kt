package com.example.myselfcustom.arch_retrofit_coroutine

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myselfcustom.base.BaseActivity
import com.example.myselfcustom.databinding.ActivityCoroutineBinding

class CoroutineActivity : BaseActivity<ActivityCoroutineBinding>() {

    private val vm by lazy {
        ViewModelProvider(this)[CoroutineViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.coroutineBtn.setOnClickListener {
            // 会重复多次监听
            initVm()
        }
    }

    private fun initVm() {
        vm.getHomePage().observe(this) {
            if (it.isSuccess) {
                Toast.makeText(this, it!!.data!![0].title, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun createViewBinding() = ActivityCoroutineBinding.inflate(layoutInflater)


}