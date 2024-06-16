package com.example.myselfcustom.sourcecodelearn.sourceokhttp

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.myselfcustom.BaseActivity
import com.example.myselfcustom.databinding.ActivityOkHttpBinding
import com.example.myselfcustom.ui.slideleftfragment.SlideLeftCommon

@Route(path = SlideLeftCommon.OKHTTP_ACTIVITY_PATH)
class OkHttpActivity : BaseActivity<ActivityOkHttpBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun createViewBinding() =
        ActivityOkHttpBinding.inflate(layoutInflater)

}