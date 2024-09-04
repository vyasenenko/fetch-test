package com.example.test.domain.repository

import com.example.test.domain.api.MainApi
import com.example.test.domain.model.ItemModel
import ru.gildor.coroutines.retrofit.Result
import ru.gildor.coroutines.retrofit.awaitResult
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val mainApi: MainApi
) {

    suspend fun getList(): Result<List<ItemModel>> {
        return mainApi.getList().awaitResult()
    }
}
