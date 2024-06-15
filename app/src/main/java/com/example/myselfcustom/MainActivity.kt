package com.example.myselfcustom

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myselfcustom.arch.CoroutineActivity
import com.example.myselfcustom.databinding.ActivityMainBinding
import com.example.myselfcustom.sourcecodelearn.apt.AptActivity
import com.example.myselfcustom.sourcecodelearn.sourceeventbus.EventBusActivity
import com.example.myselfcustom.sourcecodelearn.sourceokhttp.OkHttpActivity
import com.example.myselfcustom.sourcecodelearn.sourcerxjava.RxjavaLearnActivity
import com.example.myselfcustom.ui.customview.viewclickspan.ClickSpanFragment
import com.example.myselfcustom.ui.customview.viewlike.ThirdFragment
import com.example.myselfcustom.ui.customview.viewrect.FifthFragment
import com.example.myselfcustom.ui.customview.viewscalerv.SecondFragment
import com.example.myselfcustom.ui.customview.viewslidecard.FourthFragment
import com.example.myselfcustom.vm.LiveDataDemoViewModel

class MainActivity : BaseActivity<ActivityMainBinding>(), MainRvClickListener {

    private val vm by lazy {
        ViewModelProvider(this)[LiveDataDemoViewModel::class.java]
    }

    private val arrayPairInfo = listOf(
        Pair("放缩RV效果", SecondFragment::class.java),
        Pair("点赞效果", ThirdFragment::class.java),
        Pair("探探划卡", FourthFragment::class.java),
        Pair("自定义文本框", FifthFragment::class.java),
        Pair("OKHTTP", OkHttpActivity::class.java),
        Pair("EventBus", EventBusActivity::class.java),
        Pair("Rxjava", RxjavaLearnActivity::class.java),
        Pair("Apt", AptActivity::class.java),
        Pair("ClickSpanConflict", ClickSpanFragment::class.java),
        Pair("协程相关", CoroutineActivity::class.java)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutManager = LinearLayoutManager(this)
        val adapter = NormalAdapter(arrayPairInfo, this)
        binding.multiActivityRv.layoutManager = layoutManager
        binding.multiActivityRv.adapter = adapter
    }

    override fun createViewBinding() =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onItemClick(clazz: Class<out Any>) {
        if (clazz.simpleName.endsWith("Activity")) {
            startActivity(
                Intent(this, clazz)
            )
        } else {

        }
//        if (clazz.simpleName == "ClickSpanActivity") {
//            ARouter.getInstance().build("/test/clickSpan").navigation()
//        } else {
//            startActivity(
//                Intent(this, clazz)
//            )
//        }
    }

}