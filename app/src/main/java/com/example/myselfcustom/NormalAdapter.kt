package com.example.myselfcustom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.myselfcustom.databinding.ItemNormalRvBinding

class NormalAdapter(
    private val items: List<Pair<String, Class<out Any>>>,
    private val clickListener: CommonClickListener,
) : RecyclerView.Adapter<BaseViewHolder<Pair<String, Class<out Any>>, CommonClickListener>>() {

    // todo: onCreateViewHolder和onBindingViewHolder可以收敛为一个方法，TypeViewHolder, TypeViewAdapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        : BaseViewHolder<Pair<String, Class<out Any>>, CommonClickListener> {
        val binding = ItemNormalRvBinding.inflate(LayoutInflater.from(parent.context))
        return NormalViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<Pair<String, Class<out Any>>, CommonClickListener>,
        position: Int) {
        holder.bind(items[position], clickListener)
    }

    override fun getItemCount() = items.size
}

class NormalViewHolder(private val binding: ItemNormalRvBinding)
    : BaseViewHolder<Pair<String, Class<out Any>>, CommonClickListener>(binding.root) {

    override fun bind(item: Pair<String, Class<out Any>>, listener: CommonClickListener?) {
        binding.normalTv.text = item.first
        // todo: 不清楚为什么重新设父布局的参数，不然textView无法撑满屏幕
        binding.normalTv.rootView.layoutParams = ViewGroup.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT)
        binding.normalTv.setOnClickListener {
            listener?.onItemClick(item.second)
        }
    }
}

// 用来扩展一个Rv中存在不同类型的ViewHolder使用
abstract class BaseViewHolder<T, EVENT>(
    view: View
) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: T, event: EVENT?)
}

interface CommonClickListener {
    fun onItemClick(clazz: Class<out Any>)
}
