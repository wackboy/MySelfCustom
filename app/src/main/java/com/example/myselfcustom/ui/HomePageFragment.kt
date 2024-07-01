package com.example.myselfcustom.ui

import android.os.Bundle
import android.view.View
import com.example.myselfcustom.BaseFragment
import com.example.myselfcustom.databinding.ItemHomePageBinding

class HomePageFragment : BaseFragment<ItemHomePageBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun createViewBinding() = ItemHomePageBinding.inflate(layoutInflater)

}