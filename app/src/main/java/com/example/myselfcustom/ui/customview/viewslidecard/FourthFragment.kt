package com.example.myselfcustom.ui.customview.viewslidecard

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.myselfcustom.BaseFragment
import com.example.myselfcustom.databinding.ActivityFourthBinding
import com.example.myselfcustom.ui.slideleftfragment.SlideLeftCommon

@Route(path = SlideLeftCommon.FOURTH_FRAGMENT_PATH)
class FourthFragment : BaseFragment<ActivityFourthBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UISlideCardHelper.initHelper(this.requireContext(), binding.cardRv)
    }

    override fun createViewBinding() = ActivityFourthBinding.inflate(layoutInflater)

}