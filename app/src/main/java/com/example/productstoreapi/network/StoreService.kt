package com.example.productstoreapi.network

import com.example.productstoreapi.network.response.Product
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface StoreService {

    @GET("/products")
    fun getAllProducts(): Call<List<Product>>
}