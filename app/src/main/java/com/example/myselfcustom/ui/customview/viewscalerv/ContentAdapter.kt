package com.example.myselfcustom.ui.customview.viewscalerv

import android.content.Context
import android.graphics.Color
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.chad.library.adapter4.BaseQuickAdapter
import com.chad.library.adapter4.viewholder.QuickViewHolder
import com.example.myselfcustom.R


class ContentAdapter(private val result: List<String>) : BaseQuickAdapter<String, QuickViewHolder>() {

    override fun onBindViewHolder(holder: QuickViewHolder, position: Int, item: String?) {
        if (holder.adapterPosition % 2 == 1) {
            holder.getView<RelativeLayout>(R.id.free_game_item_layout).setBackgroundColor(Color.RED)
        } else {
            holder.getView<RelativeLayout>(R.id.free_game_item_layout).setBackgroundColor(Color.BLUE)
        }
    }

    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int
    ): QuickViewHolder {
        return QuickViewHolder(R.layout.item_content, parent)
    }

    override fun getItemCount(items: List<String>) = result.size

}

