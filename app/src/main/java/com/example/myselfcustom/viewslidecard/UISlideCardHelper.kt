package com.example.myselfcustom.viewslidecard

import android.content.Context
import android.util.TypedValue
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.myselfcustom.R

class UISlideCardHelper(
    context: Context,
    recyclerView: RecyclerView
) {
    companion object {
        fun initHelper(context: Context, recyclerView: RecyclerView) =
            UISlideCardHelper(context, recyclerView).apply {
                paramsMaxShowCount = 2
                paramsScaleGap = 0.05f
                paramsTransYGap =
                    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15f, context.resources.displayMetrics)
            }
    }

    private var paramsMaxShowCount: Int = 0
    private var paramsScaleGap: Float = 0f
    private var paramsTransYGap: Float = 0f

    init {
        val layoutManager = SlideCardManager()
        val adapter = CardAdapter(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        val lists = initData()
        adapter.setData(lists)
        ItemTouchHelper(SlideCardCallBack(adapter, lists)).attachToRecyclerView(recyclerView)
    }

    private fun initData(): List<CardMeta> {
        val list = mutableListOf<CardMeta>()
        for (i in 0 until 10 step 2) {
            println("Rsnfosfojfo: $i")
            list.add(CardMeta(R.drawable.test, i + 1))
            list.add(CardMeta(R.drawable.img_2000x3000, i + 2))
        }
        return list
    }

}