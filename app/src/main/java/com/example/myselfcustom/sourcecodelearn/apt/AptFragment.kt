package com.example.myselfcustom.sourcecodelearn.apt

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.myselfcustom.BaseFragment
import com.example.myselfcustom.databinding.ActivityAptBinding
import com.example.myselfcustom.ui.slideleftfragment.SlideLeftCommon

@Route(path = SlideLeftCommon.APT_ACTIVITY_PATH)
class AptFragment : BaseFragment<ActivityAptBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun createViewBinding(): ActivityAptBinding {
        return ActivityAptBinding.inflate(layoutInflater)
    }
}