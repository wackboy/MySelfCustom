package com.example.myselfcustom.ui.customview.viewclickspan

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.myselfcustom.BaseFragment
import com.example.myselfcustom.R
import com.example.myselfcustom.databinding.ActivityClickspanBinding
import com.example.myselfcustom.ui.slideleftfragment.SlideLeftCommon
import com.example.myselfcustom.utils.MyLinkMovementMethod

@Route(path = SlideLeftCommon.CLICK_SPAN_FRAGMENT_PATH)
class ClickSpanFragment : BaseFragment<ActivityClickspanBinding>() {


    private var flag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    @SuppressLint("SetTextI18n", "ClickableViewAccessibility")
    private fun initView() {
        binding.clickTv.text = "ashamed afbaf af"
        binding.clickTv.apply {
            setOnClickListener {
                Toast.makeText(context, "click Text", Toast.LENGTH_SHORT).show()
            }
        }
        binding.clickTv.setOnTouchListener(MyLinkMovementMethod().getInstance())

        binding.clickContainer.setOnClickListener {
            flag = !flag
            Toast.makeText(this.requireActivity(), "click clickContainer", Toast.LENGTH_SHORT).show()
            if (flag) {
                adjustAttr(false)
                binding.clickContainer.setBackgroundColor(this.requireActivity().getColor(R.color.buttonBackground))
            } else {
                adjustAttr(true)
                binding.clickContainer.setBackgroundColor(this.requireActivity().getColor(R.color.white))
            }
        }
    }

    private fun adjustAttr(enableClick: Boolean) {
        val arrayClickView = arrayOf(binding.clickTv)
        arrayClickView.forEach {
            it.apply {
                isClickable = enableClick
                isFocusable = enableClick
                movementMethod = if (enableClick) LinkMovementMethod.getInstance() else null
            }
        }
    }


    override fun createViewBinding() =
        ActivityClickspanBinding.inflate(layoutInflater)

}