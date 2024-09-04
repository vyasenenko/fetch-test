package com.example.test.ui.list

import com.example.fetch_test.BR
import com.example.fetch_test.R
import com.example.test.common.recycleradapter.ItemViewModel
import com.example.test.common.recycleradapter.RecyclerItem
import com.example.test.common.recycleradapter.comparator.RecyclerItemComparator
import com.example.test.domain.model.ItemModel

class ListItemViewModel(override val item: ItemModel) :
    ItemViewModel<ItemModel>(), RecyclerItemComparator {

    fun name() =
        item.name.toString()

    override fun isSameItem(other: Any): Boolean {
        if (other is ListItemViewModel) {
            return other.item.id == item.id
        }
        return false
    }

    override fun isSameContent(other: Any): Boolean {
        if (other is ListItemViewModel) {
            return other.item.id == item.id
        }
        return false
    }

    fun toRecycleItem() =
        RecyclerItem(this, R.layout.item, BR.viewModel)
}