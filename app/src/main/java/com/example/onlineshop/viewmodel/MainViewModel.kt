package com.example.onlineshop.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.db.ShopDatabase
import com.example.onlineshop.db.ShopRepository
import com.example.onlineshop.model.FavoritesItem
import com.example.onlineshop.network.StoreApi
import com.example.onlineshop.model.Product
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    //LIVE DATA
    private val products = MutableLiveData<List<Product>>()
    val productsData: LiveData<List<Product>> get() = products

    //REPOSITORY
    var repository: ShopRepository? = null
    var allFavorites: LiveData<List<FavoritesItem>>? = null

    fun init(app: Application){
        val dao = ShopDatabase.getDatabaseInstance(app, Gson()).getDao()
        repository = ShopRepository(dao)
        allFavorites = repository?.allFavorites
    }

    //FAVORITES MANAGE
    fun addToFavorites(favItem: FavoritesItem){
        viewModelScope.launch {
            repository?.addToFavorites(favItem)
        }
    }
    fun deleteFromFavorites(favItem: FavoritesItem) {
        viewModelScope.launch {
            repository?.deleteFromFavorites(favItem)
        }
    }


    //get all product
    fun getProducts() {
        viewModelScope.launch {
            StoreApi.service.getAllProducts().enqueue(object : Callback<List<Product>?> {
                override fun onResponse(call: Call<List<Product>?>, response: Response<List<Product>?>) {
                    if (response.isSuccessful) {
                        response.body()?.let { productList ->
                            Log.d("TAG", "PRODUCT SERVICE SUCCESS")
                            products.postValue(productList)
                        } ?: kotlin.run {
                            Log.d("TAG", "EMPTY BODY")
                        }
                    } else {
                        Log.d("TAG", "SERVICE FAILED: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<List<Product>?>, t: Throwable) {
                    Log.d("TAG", "SERVICE FAILED: ${t.message}")
                }
            })
        }
    }


    //get jewelery
    fun getJewelery() {

        viewModelScope.launch {
            StoreApi.service.getJeweleryProduct().enqueue(object : Callback<List<Product>?> {
                override fun onResponse(call: Call<List<Product>?>, response: Response<List<Product>?>) {
                    if(response.isSuccessful){
                        response.body()?.let { productList ->
                            Log.d("TAG", "JEWELERY SERVICE SUCCESS")
                            products.postValue(productList)
                        }?: kotlin.run {
                            Log.d("TAG", "EMPTY BODY")
                        }
                    } else{
                        Log.d("TAG", "SERVICE FAILED: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<List<Product>?>, t: Throwable) {
                    Log.d("TAG", "SERVICE FAILED: ${t.message}")
                }
            })
        }
    }


    //get electronic
    fun getElectronic() {

        viewModelScope.launch {
            StoreApi.service.getElectronicProduct().enqueue(object : Callback<List<Product>?> {
                override fun onResponse(call: Call<List<Product>?>, response: Response<List<Product>?>) {
                    if(response.isSuccessful){
                        response.body()?.let { productList ->
                            Log.d("TAG", "ELECTRONIC SERVICE SUCCESS")
                            products.postValue(productList)
                        }?: kotlin.run {
                            Log.d("TAG", "EMPTY BODY")
                        }
                    } else{
                        Log.d("TAG", "SERVICE FAILED: ${response.message()}")
                    }

                }

                override fun onFailure(call: Call<List<Product>?>, t: Throwable) {
                    Log.d("TAG", "SERVICE FAILED: ${t.message}")
                }
            })
        }
    }


    //get men clothes
    fun getMen() {

        viewModelScope.launch {
            StoreApi.service.getMenProduct().enqueue(object : Callback<List<Product>?> {
                override fun onResponse(call: Call<List<Product>?>, response: Response<List<Product>?>) {
                    if(response.isSuccessful){
                        response.body()?.let { productList ->
                            Log.d("TAG", "MEN SERVICE SUCCESS")
                            products.postValue(productList)
                        }?: kotlin.run {
                            Log.d("TAG", "EMPTY BODY")
                        }
                    } else{
                        Log.d("TAG", "SERVICE FAILED: ${response.message()}")
                    }

                }

                override fun onFailure(call: Call<List<Product>?>, t: Throwable) {
                    Log.d("TAG", "SERVICE FAILED: ${t.message}")
                }
            })
        }
    }


    //get women clothes
    fun getWomen() {

        viewModelScope.launch {
            StoreApi.service.getWomenProduct().enqueue(object : Callback<List<Product>?> {
                override fun onResponse(call: Call<List<Product>?>, response: Response<List<Product>?>) {
                    if(response.isSuccessful){
                        response.body()?.let { productList ->
                            Log.d("TAG", "WOMEN SERVICE SUCCESS")
                            products.postValue(productList)
                        }?: kotlin.run {
                            Log.d("TAG", "EMPTY BODY")
                        }
                    } else{
                        Log.d("TAG", "SERVICE FAILED: ${response.message()}")
                    }

                }

                override fun onFailure(call: Call<List<Product>?>, t: Throwable) {
                    Log.d("TAG", "SERVICE FAILED: ${t.message}")
                }
            })
        }
    }




}