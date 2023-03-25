package com.example.productstoreapi.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object StoreApi {

    val retrofit: StoreService by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StoreService::class.java)
    }

}