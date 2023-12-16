package com.example.myselfcustom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myselfcustom.databinding.ItemNormalRvBinding

class NormalAdapter(
    private val items: List<Pair<String, Class<out AppCompatActivity>>>,
    private val clickListener: MainRvClickListener,
) : RecyclerView.Adapter<BaseViewHolder<Pair<String, Class<out AppCompatActivity>>, MainRvClickListener>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        : BaseViewHolder<Pair<String, Class<out AppCompatActivity>>, MainRvClickListener> {
        val binding = ItemNormalRvBinding.inflate(LayoutInflater.from(parent.context))
        return NormalViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<Pair<String, Class<out AppCompatActivity>>, MainRvClickListener>,
        position: Int) {
        holder.bind(items[position], clickListener)
    }

    override fun getItemCount() = items.size
}

class NormalViewHolder(private val binding: ItemNormalRvBinding)
    : BaseViewHolder<Pair<String, Class<out AppCompatActivity>>, MainRvClickListener>(binding.root) {

    override fun bind(item: Pair<String, Class<out AppCompatActivity>>, listener: MainRvClickListener?) {
        binding.normalTv.text = item.first
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

interface MainRvClickListener {
    fun onItemClick(clazz: Class<out AppCompatActivity>)
}
