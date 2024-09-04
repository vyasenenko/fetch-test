package com.example.test.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.test.common.CommonViewModel
import com.example.test.common.recycleradapter.RecyclerItem
import com.example.test.domain.model.ItemModel
import com.example.test.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.gildor.coroutines.retrofit.getOrThrow
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor() : CommonViewModel() {

    @Inject
    lateinit var repository: MainRepository

    private val mList: MutableLiveData<List<Pair<Int, List<ItemModel>>>> = MutableLiveData()

    val list: LiveData<List<RecyclerItem>>
        get() = mList.map { groupList ->
            groupList.flatMap { oneGroup ->
                val list = mutableListOf<RecyclerItem>()
                list += GroupItemViewModel(oneGroup.first).toRecycleItem()
                list += oneGroup.second.map {
                    ListItemViewModel(it).toRecycleItem()
                }
                list
            }
        }

    fun showList() = viewModelScope.launch {
        tryHttp {
            val listResult = repository.getList().getOrThrow()
                .asSequence()
                .filter { !it.name.isNullOrBlank() }
                .sortedWith(compareBy(ItemModel::listId, ItemModel::name))
                .groupBy { it.listId }
                .toList()
                .sortedBy { it.first }

            mList.postValue(listResult)
        }
    }
}