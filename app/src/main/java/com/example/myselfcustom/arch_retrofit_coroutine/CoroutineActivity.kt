package com.example.myselfcustom.arch_retrofit_coroutine

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myselfcustom.base.BaseActivity
import com.example.myselfcustom.databinding.ActivityCoroutineBinding
import com.example.myselfcustom.utils.observeState
import com.example.myselfcustom.utils.throttle

class CoroutineActivity : BaseActivity<ActivityCoroutineBinding>() {

    private val vm by lazy {
        ViewModelProvider(this)[CoroutineViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.coroutineBtn.setOnClickListener(
            View.OnClickListener {
                initVm()
            }.throttle()
        )
    }

    private fun initVm() {
        vm.getHomePage().observeState(this) {
            onSuccess = {
                if (it?.isNotEmpty() == true) {
                    Toast.makeText(this@CoroutineActivity, it[0].title, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun createViewBinding() = ActivityCoroutineBinding.inflate(layoutInflater)


}