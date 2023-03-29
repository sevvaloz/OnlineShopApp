package com.example.productstoreapi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productstoreapi.network.StoreApi
import com.example.productstoreapi.network.response.Product
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModel: ViewModel() {

    private val products = MutableLiveData<List<Product>>()
    val productsData: LiveData<List<Product>> get() = products

/*    private val jproducts = MutableLiveData<List<Product>>()
    val jproductsData: LiveData<List<Product>> get() = jproducts*/


    //all product
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


    //jewelery
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


    //electronic
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


    //men
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


    //women
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