package com.example.myselfcustom.arch_retrofit_coroutine

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myselfcustom.base.BaseActivity
import com.example.myselfcustom.databinding.ActivityCoroutineBinding
import com.example.myselfcustom.utils.observeState
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
        initVm()
        initUi()
    }

    private fun initUi() {
//        binding.coroutineBtn.setOnClickListener(
//            View.OnClickListener {
//            }.throttle()
//        )
        var i = 0
        binding.liveDataOnce.setOnClickListener {
            i = i + 1
            vm.setLiveData(i.toString())
            vm.homePageDataSource.getBannerInfoSequence()
        }
    }


    private fun initVm() {
//        vm.getHomePage().observeState(this) {
//            onSuccess = {
//                if (it?.isNotEmpty() == true) {
//                    Toast.makeText(this@CoroutineActivity, it[0].title, Toast.LENGTH_SHORT).show()
//                }
//            }
//        }

//        vm.getHomePage().observeOnce(this) {
//            if (it.isSuccess) {
//                Toast.makeText(this@CoroutineActivity, it.data!![0].title, Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        vm.mockLiveData.observeOnce(this) {
//            Toast.makeText(this@CoroutineActivity, it, Toast.LENGTH_SHORT).show()
//        }

//        repeat(10) {
//            lifecycleScope.launch {
//                delay(100)
//                vm.homePageDataSource.getBannerInfoSequence()
//            }
//        }
        var i = 0
//        vm.homePageDataSource.mediatorLiveData.observeState(this) {
//            onSuccess = {
//                println("i的次数：$i")
//                i += 1
//                if (it?.isNotEmpty() == true) {
//                    Toast.makeText(this@CoroutineActivity, it[0].title, Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
        vm.getArticleContent().observeState(this) {
            onSuccess = {
                if (it != null) {
                    Toast.makeText(this@CoroutineActivity, it.datas?.get(0)?.title ?: "hahhasdnasdaoda", Toast.LENGTH_SHORT).show()
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
