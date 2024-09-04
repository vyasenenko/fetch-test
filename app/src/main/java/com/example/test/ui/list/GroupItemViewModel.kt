package com.example.test.ui.list

import com.example.fetch_test.BR
import com.example.fetch_test.R
import com.example.test.common.recycleradapter.ItemViewModel
import com.example.test.common.recycleradapter.RecyclerItem
import com.example.test.common.recycleradapter.comparator.RecyclerItemComparator

class GroupItemViewModel(override val item: Int) :
    ItemViewModel<Int>(), RecyclerItemComparator {


    fun groupName() =
        item.toString()

    override fun isSameItem(other: Any): Boolean {
        if (other is GroupItemViewModel) {
            return other.item == item
        }
        return false
    }

    override fun isSameContent(other: Any): Boolean {
        if (other is GroupItemViewModel) {
            return other.item == item
        }
        return false
    }

    fun toRecycleItem() =
        RecyclerItem(this, R.layout.item_group, BR.viewModel)
}