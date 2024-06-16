package com.example.myselfcustom.ui.customview.viewscalerv

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.myselfcustom.BaseFragment
import com.example.myselfcustom.databinding.ActivitySecondBinding
import com.example.myselfcustom.ui.slideleftfragment.SlideLeftCommon

@Route(path = SlideLeftCommon.SECOND_FRAGMENT_PATH)
class SecondFragment : BaseFragment<ActivitySecondBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UIScaleRVHelper(this.requireContext(), binding.rvContent)
    }

    override fun createViewBinding() = ActivitySecondBinding.inflate(layoutInflater)

}









