package com.example.myselfcustom.ui.customview.viewscalerv

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myselfcustom.utils.ScreenUtils

class ContentItemDecoration(
    context: Context
) : RecyclerView.ItemDecoration() {

    companion object {
        const val SCREEN_WIDTH = 1080
        const val SCREEN_HEIGHT = 1920
    }

    private var mLeftMargin: Int = 0
    private var newMargin: Int = 0

    init {
        mLeftMargin = (SCREEN_WIDTH - ScreenUtils.dp2px(context, 327f)) / 2
        newMargin = ScreenUtils.dp2px(context, -300f)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val itemCount = parent.adapter?.itemCount ?: 0
        var leftMargin = 0
        var rightMargin = 0
//        leftMargin = if (position == 0) mLeftMargin else newMargin
        leftMargin = if (position == 0) mLeftMargin else 0

        rightMargin = if (position == itemCount - 1) newMargin else 0
        val layoutParams = view.layoutParams as RecyclerView.LayoutParams
        layoutParams.setMargins(leftMargin, 0, rightMargin, 0)
        super.getItemOffsets(outRect, view, parent, state)
    }

}