package com.example.test.domain.api

import com.example.test.domain.model.ItemModel
import retrofit2.Call
import retrofit2.http.GET

interface MainApi {

    @GET("/hiring.json")
    fun getList(): Call<List<ItemModel>>
}