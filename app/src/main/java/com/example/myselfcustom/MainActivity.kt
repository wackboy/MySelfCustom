package com.example.myselfcustom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apt_annotation.AptAnnotation
import com.example.apt_api.ApiImpl
import com.example.myselfcustom.base.BaseActivity
import com.example.myselfcustom.databinding.ActivityMainBinding
import com.example.myselfcustom.sourcecodelearn.apt.AptActivity
import com.example.myselfcustom.sourcecodelearn.sourceeventbus.EventBusActivity
import com.example.myselfcustom.sourcecodelearn.sourceokhttp.OkHttpActivity
import com.example.myselfcustom.sourcecodelearn.sourcerxjava.RxjavaLearnActivity
import com.example.myselfcustom.viewlike.ThirdActivity
import com.example.myselfcustom.viewrect.FifthActivity
import com.example.myselfcustom.viewscalerv.SecondActivity
import com.example.myselfcustom.viewslidecard.FourthActivity

@AptAnnotation(desc = "hello ya")
class MainActivity : BaseActivity<ActivityMainBinding>(), MainRvClickListener {

    private val arrayPairInfo = listOf(
        Pair("放缩RV效果", SecondActivity::class.java),
        Pair("点赞效果", ThirdActivity::class.java),
        Pair("探探划卡", FourthActivity::class.java),
        Pair("自定义文本框", FifthActivity::class.java),
        Pair("OKHTTP", OkHttpActivity::class.java),
        Pair("EventBus", EventBusActivity::class.java),
        Pair("Rxjava", RxjavaLearnActivity::class.java),
        Pair("Apt", AptActivity::class.java)
    )

    @AptAnnotation(desc = "happy chrismas")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutManager = LinearLayoutManager(this)
        val adapter = NormalAdapter(arrayPairInfo, this)
        binding.multiActivityRv.layoutManager = layoutManager
        binding.multiActivityRv.adapter = adapter
        ApiImpl.init()
    }

    override fun createViewBinding() =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onItemClick(clazz: Class<out AppCompatActivity>) {
        startActivity(
            Intent(this, clazz)
        )
    }

}