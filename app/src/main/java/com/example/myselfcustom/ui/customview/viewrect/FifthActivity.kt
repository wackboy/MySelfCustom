package com.example.myselfcustom.ui.customview.viewrect

import android.os.Bundle
import com.example.myselfcustom.BaseActivity
import com.example.myselfcustom.databinding.ActivityFifthBinding

class FifthActivity : BaseActivity<ActivityFifthBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = mutableListOf<PrivilegesItem>()
        data.add(PrivilegesItem(text = "1.免费解锁996功能"))
        data.add(
            PrivilegesItem(
                text = "2.享受无限制高额大饼，每天都可以看老板画饼哦～",
                action = "立即开通"
            )
        )
        data.add(PrivilegesItem(text = "3.可以免费加班到凌晨6点，无需任何加班费"))
        data.add(PrivilegesItem(text = "4.每天可以带薪上厕所5分钟"))
        data.add(PrivilegesItem(text = "5.更多特权等你解锁,点击了解更多", action = "点我解锁"))
        val adapter = PrivilegesAdapter()
        binding.customTextRv.adapter = adapter
        adapter.setData(data)
    }

    override fun createViewBinding() = ActivityFifthBinding.inflate(layoutInflater)
}

data class PrivilegesItem(
    val text: String,
    val action: String? = null
)