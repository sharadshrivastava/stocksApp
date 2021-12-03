package com.test.app.ui.common

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


@BindingAdapter(value = ["items", "itemLayout", "clickListener"], requireAll = true)
fun <T> configureRecyclerView(
    recyclerView: RecyclerView,
    items: List<Any>?,
    layoutId: Int,
    clickListener: ItemClickListener
) {
    if (recyclerView.adapter == null) {
        recyclerView.adapter = GenericRecyclerViewAdapter(items, layoutId, clickListener)
    } else {
        (recyclerView.adapter as GenericRecyclerViewAdapter).setItems(items)
    }
}