package com.example.myselfcustom.viewslidecard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnScrollChangeListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myselfcustom.BaseViewHolder
import com.example.myselfcustom.databinding.ItemCardViewBinding

class CardAdapter(
    context: Context
): RecyclerView.Adapter<BaseViewHolder<Pair<CardMeta, Int>, Any>>() {

    private var lists: List<CardMeta>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Pair<CardMeta, Int>, Any> {
        return SlideCardViewHolder(ItemCardViewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Pair<CardMeta, Int>, Any>, position: Int) {
        lists ?: return
        holder.bind(Pair(lists!![position], position), null)
    }

    fun setData(lists: List<CardMeta>) {
        this.lists = lists
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = lists?.size ?: 0

}

class SlideCardViewHolder(private val binding: ItemCardViewBinding)
    : BaseViewHolder<Pair<CardMeta, Int>, Any>(binding.root) {

    override fun bind(item: Pair<CardMeta, Int>, event: Any?) {
        binding.ivCover.setImageResource(item.first.resId)
        binding.tvIndex.text = item.second.toString()
        binding.scrollView.setOnScrollChangeListener(object : OnScrollChangeListener {
            override fun onScrollChange(
                v: View?,
                scrollX: Int,
                scrollY: Int,
                oldScrollX: Int,
                oldScrollY: Int
            ) {
                val childView = (v as NestedScrollConflictView)?.getChildAt(0) ?: return
                // childView.height - v.height表示可滚动的距离
                // scrollY已滚动的距离
                // scrollView本身是不会移动的，只有屏幕的高度，childView的高度要高于scrollView
                val process = scrollY * 1f / (childView.height - v.height)
                binding.viewProgress.translationY =
                    (binding.viewProgressBackground.height - binding.viewProgress.height) * process
            }

        })
    }
}
