package com.example.myselfcustom.arch

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.myselfcustom.BaseActivity
import com.example.myselfcustom.arch.vm.CoroutineViewModel
import com.example.myselfcustom.databinding.ActivityCoroutineBinding
import com.example.myselfcustom.ui.slideleftfragment.SlideLeftCommon
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

@Route(path = SlideLeftCommon.COROUTINE_ACTIVITY_PATH)
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

        // 通过liveData的形式获取 在janus中不被推荐
//        vm.getArticleContent().observeState(this) {
//            onSuccess = {
//                if (it != null) {
//                    Toast.makeText(this@CoroutineActivity, it.datas?.get(0)?.title ?: "hahhasdnasdaoda", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }

        vm.pageNum.drop(1).onEach {
            Log.d(TAG, "fetchArticleNum: $it" + "线程是：" + Thread.currentThread().name)
        }.launchIn(lifecycleScope)

        lifecycleScope.launch {
            val response = vm.fetchArticleContent()
            Log.d(TAG, "fetchArticleContent回调当前的线程是：" + Thread.currentThread().name)
            if (response.isSuccess) {
                Toast.makeText(this@CoroutineActivity, response.data?.datas?.get(0)?.title ?: "hahhasdnasdaoda", Toast.LENGTH_SHORT).show()
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
