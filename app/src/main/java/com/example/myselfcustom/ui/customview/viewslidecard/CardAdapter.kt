package com.example.myselfcustom.ui.customview.viewslidecard

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
): RecyclerView.Adapter<BaseViewHolder<CardMeta, Any>>() {

    private var lists: MutableList<CardMeta>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<CardMeta, Any> {
        return SlideCardViewHolder(ItemCardViewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<CardMeta, Any>, position: Int) {
        lists ?: return
        holder.bind(lists!![position], null)
    }

    fun setData(lists: List<CardMeta>) {
        this.lists = lists as MutableList<CardMeta>
        notifyDataSetChanged()
    }

    fun updateData(position: Int) {
        lists ?: return
        lists?.let {
            val meta = it[position]
            it.removeAt(position)
            it.add(it.lastIndex, meta)
            notifyDataSetChanged()
        }
//        notifyItemChanged(position)
    }

    override fun getItemCount(): Int = lists?.size ?: 0

}

class SlideCardViewHolder(private val binding: ItemCardViewBinding)
    : BaseViewHolder<CardMeta, Any>(binding.root) {

    override fun bind(item: CardMeta, event: Any?) {
        binding.ivCover.setImageResource(item.resId)
        binding.tvIndex.text = item.index.toString()
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
