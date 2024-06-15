package com.example.myselfcustom.ui.customview.viewrect

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myselfcustom.BaseViewHolder
import com.example.myselfcustom.databinding.ItemCustomTextViewBinding

class PrivilegesAdapter(
) : RecyclerView.Adapter<BaseViewHolder<PrivilegesItem, Any>>() {

    private var lists: MutableList<PrivilegesItem>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        : BaseViewHolder<PrivilegesItem, Any> {
        return PrivilegesViewHolder(ItemCustomTextViewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<PrivilegesItem, Any>, position: Int) {
        lists ?: return
        holder.bind(lists!![position], null)
    }

    fun setData(data: List<PrivilegesItem>) {
        this.lists = data as MutableList<PrivilegesItem>
        notifyDataSetChanged()
    }

    override fun getItemCount() = lists?.size ?: 0
}

class PrivilegesViewHolder(private val binding: ItemCustomTextViewBinding)
    : BaseViewHolder<PrivilegesItem, Any>(binding.root) {

    override fun bind(item: PrivilegesItem, event: Any?) {
        binding.privilegesItem.text = item.text
        binding.tvAction.text = item.action
    }

}
