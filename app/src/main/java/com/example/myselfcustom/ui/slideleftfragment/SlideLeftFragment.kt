package com.example.myselfcustom.ui.slideleftfragment

import android.os.Bundle
import android.view.View
import com.example.myselfcustom.BaseFragment
import com.example.myselfcustom.databinding.FragmentLeftSlideBinding

class SlideLeftFragment : BaseFragment<FragmentLeftSlideBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun createViewBinding() =
        FragmentLeftSlideBinding.inflate(layoutInflater)
}