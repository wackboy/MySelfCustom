package com.example.myselfcustom.ui

import android.os.Bundle
import android.view.View
import com.example.myselfcustom.BaseFragment
import com.example.myselfcustom.databinding.ItemOtherPageBinding

class OtherPageFragment : BaseFragment<ItemOtherPageBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun createViewBinding() = ItemOtherPageBinding.inflate(layoutInflater)
}