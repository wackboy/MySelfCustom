package com.example.myselfcustom.viewslidecard

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SlideCardManager : RecyclerView.LayoutManager() {

    override fun generateDefaultLayoutParams() =
        RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State?) {
        // 直接使用父类的回收功能
        detachAndScrapAttachedViews(recycler)
        for (i in 0 until itemCount) {
            val view = recycler.getViewForPosition(i)
            if (view.scaleX != 1f) view.scaleX = 1f
            if (view.scaleY != 1f) view.scaleY = 1f
            addView(view)
            measureChildWithMargins(view, 0, 0)
            val viewWidth = getDecoratedMeasuredWidth(view)
            val viewHeight = getDecoratedMeasuredHeight(view)
            val widthSpace = width - viewWidth
            val heightSpace = height - viewHeight
            // 将所有的ItemView放置在视图中央
            layoutDecoratedWithMargins(view, widthSpace / 2, heightSpace / 2,
                widthSpace / 2 + viewWidth, heightSpace / 2 + viewHeight)

        }
    }

}