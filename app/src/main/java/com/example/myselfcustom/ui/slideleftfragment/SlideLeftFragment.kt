package com.example.myselfcustom.ui.slideleftfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.example.myselfcustom.BaseFragment
import com.example.myselfcustom.MainRvClickListener
import com.example.myselfcustom.NormalAdapter
import com.example.myselfcustom.R
import com.example.myselfcustom.databinding.FragmentLeftSlideBinding

class SlideLeftFragment : BaseFragment<FragmentLeftSlideBinding>(), MainRvClickListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(requireContext())
        val adapter = NormalAdapter(arrayPairInfo, this)
        binding.leftSlideRv.layoutManager = layoutManager
        binding.leftSlideRv.adapter = adapter
    }

    override fun createViewBinding() =
        FragmentLeftSlideBinding.inflate(layoutInflater)

    override fun onItemClick(clazz: Class<out Any>) {
        val path = routePair.find {
            it.first == clazz.simpleName
        }?.second
        if (path == null) {
            return
        }
        if (clazz.simpleName.endsWith("Activity")) {
            ARouter.getInstance().build(path).navigation()
        } else {
            val fragment = ARouter.getInstance().build(path).navigation() as? Fragment
            fragment?.let {
                this@SlideLeftFragment.activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.content_frame, it)
                    ?.commit()
            }
        }
    }
}