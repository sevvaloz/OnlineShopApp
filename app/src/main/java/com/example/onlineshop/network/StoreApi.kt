package com.example.onlineshop.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal object StoreApi {

    private const val BASE_URL = "https://fakestoreapi.com"
    private lateinit var httpClient: OkHttpClient.Builder

    //Service
    val service: StoreService by lazy{

        httpClient = getOkHttpClient()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StoreService::class.java)
    }

    //Interrupter
    private fun getOkHttpClient() : OkHttpClient.Builder {

        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(60 , TimeUnit.SECONDS)
        httpClient.readTimeout(60 , TimeUnit.SECONDS)
        httpClient.writeTimeout(60 , TimeUnit.SECONDS)

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(httpLoggingInterceptor)

        return httpClient
    }

}