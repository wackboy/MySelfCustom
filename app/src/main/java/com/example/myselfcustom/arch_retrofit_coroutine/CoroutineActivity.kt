package com.example.myselfcustom.arch_retrofit_coroutine

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myselfcustom.base.BaseActivity
import com.example.myselfcustom.databinding.ActivityCoroutineBinding
import com.example.myselfcustom.utils.observeState
import com.example.myselfcustom.utils.throttle
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

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

fun main() = runBlocking<Unit> {
    val channel = Channel<String>()
    launch {
        channel.send("A1")
        channel.send("A2")
        log("A done")
    }
    launch {
        channel.send("B1")
        log("B done")
    }
    launch {
        repeat(3) {
            val x = channel.receive()
            log(x)
        }
    }
    withTimeout(1500) {
        repeat(100) {
            println("I'm sleeping $it...")
            delay(100)
        }
    }
}

fun log(message: Any?) {
    println("[${Thread.currentThread().name}] $message")
}
