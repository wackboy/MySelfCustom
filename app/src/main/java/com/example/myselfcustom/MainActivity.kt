package com.example.myselfcustom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myselfcustom.base.BaseActivity
import com.example.myselfcustom.databinding.ActivityMainBinding
import com.example.myselfcustom.viewlike.ThirdActivity
import com.example.myselfcustom.viewscalerv.SecondActivity

class MainActivity : BaseActivity<ActivityMainBinding>(), MainRvClickListener {

    private val arrayPairInfo = listOf(
        Pair("放缩RV效果", SecondActivity::class.java),
        Pair("点赞效果", ThirdActivity::class.java)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutManager = LinearLayoutManager(this)
        binding.multiActivityRv.layoutManager = layoutManager
        val adapter = NormalAdapter(arrayPairInfo, this)
        binding.multiActivityRv.adapter = adapter
    }

    override fun createViewBinding() =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onItemClick(clazz: Class<out AppCompatActivity>) {
        startActivity(
            Intent(this, clazz)
        )
    }

}