package com.example.myselfcustom.viewslidecard

import android.graphics.Canvas
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class SlideCardCallBack @JvmOverloads constructor(
    adapter: CardAdapter,
    lists: List<CardMeta>,
    dragDirs: Int = 0,
    swipeDirs: Int = ItemTouchHelper.LEFT
            or ItemTouchHelper.RIGHT
            or ItemTouchHelper.UP
            or ItemTouchHelper.DOWN
) :
    ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        viewHolder.itemView.rotation = 0f
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val curView = viewHolder.itemView
        // 向左滑动
        if (dX < 0) {
            curView.pivotX = 0f
        } else if (dX > 0) {
            curView.pivotX = curView.width.toFloat()
        }
        curView.pivotY = 0f
        curView.rotation = -dX / 50
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    // 设置不生效回弹距离
    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder) = 0.38f

}
