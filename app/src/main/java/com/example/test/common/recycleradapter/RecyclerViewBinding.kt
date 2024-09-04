package com.example.test.common.recycleradapter

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView


@BindingAdapter(
    "items",
    "lifecycleOwner",
    requireAll = false
)
fun RecyclerView.setRecyclerViewItems(
    items: List<RecyclerItem>?,
    lifecycleOwner: LifecycleOwner?,
) {
    var adapter = (this.adapter as? DataBindingRecyclerAdapter)
    if (adapter == null) {
        adapter = DataBindingRecyclerAdapter()
        this.adapter = adapter
    }

    adapter.lifecycleOwner = lifecycleOwner
    adapter.submitList(items.orEmpty())
}


@BindingAdapter(
    "setVisibility"
)
fun View.setVisibility(
    bool: Boolean?,
) {
    if (bool == true) {
        this.isVisible = bool
    } else {
        this.isVisible = false
    }
}

