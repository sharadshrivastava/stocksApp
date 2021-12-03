package com.test.app.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.test.app.BR

class GenericRecyclerViewAdapter(
    var list: List<Any>?,
    private val layoutId: Int,
    private val clickListener: ItemClickListener
) : RecyclerView.Adapter<GenericRecyclerViewAdapter.GenericViewHolder<Any>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<Any> {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, layoutId, parent, false)
        return GenericViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: GenericViewHolder<Any>, position: Int) {
        holder.bind(list?.getOrNull(position))
    }

    override fun getItemCount(): Int {
        return list.orEmpty().size
    }

    fun setItems(list: List<Any>?) {
        this.list = list
        notifyDataSetChanged()
    }

    class GenericViewHolder<T>(
        private val binding: ViewDataBinding, private val clickListener: ItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: T?) {
            binding.setVariable(BR.item, item)
            itemView.setOnClickListener {
                clickListener.onItemClick(item)
            }
        }
    }
}

interface ItemClickListener {
    fun onItemClick(item: Any?)
}