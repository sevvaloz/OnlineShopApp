package com.example.productstoreapi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productstoreapi.network.State
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

    private val state = MutableLiveData<State>()
    val stateData: LiveData<State> get() = state

    fun getProducts(){

        state.value = State.LOADING

        viewModelScope.launch {
            //all product
            StoreApi.service.getAllProducts().enqueue(object : Callback<List<Product>?> {
                override fun onResponse(call: Call<List<Product>?>, response: Response<List<Product>?>) {

                    if(response.isSuccessful){
                        response.body()?.let { productList ->
                            Log.d("TAG", "SERVICE SUCCESS")
                            state.value = State.COMPLETED
                            products.postValue(productList)
                        }?: kotlin.run {
                            state.value = State.ERROR
                            Log.d("TAG", "EMPTY BODY")
                        }
                    } else{
                        state.value = State.ERROR
                        Log.d("TAG", "SERVICE FAILED: ${response.message()}")
                    }

                }

                override fun onFailure(call: Call<List<Product>?>, t: Throwable) {
                    state.value = State.ERROR
                    Log.d("TAG", "SERVICE FAILED: ${t.message}")
                }
            })

        }

    }

    //jewelery
    fun getJeweleryProducts() {

        state.value = State.LOADING

        StoreApi.service.getJeweleryProduct().enqueue(object : Callback<List<Product>?> {
            override fun onResponse(call: Call<List<Product>?>, response: Response<List<Product>?>) {

                if(response.isSuccessful){
                    response.body()?.let { productList ->
                        Log.d("TAG", "SERVICE SUCCESS")
                        state.value = State.COMPLETED
                        products.postValue(productList)
                    }?: kotlin.run {
                        state.value = State.ERROR
                        Log.d("TAG", "EMPTY BODY")
                    }
                } else{
                    state.value = State.ERROR
                    Log.d("TAG", "SERVICE FAILED: ${response.message()}")
                }

            }

            override fun onFailure(call: Call<List<Product>?>, t: Throwable) {
                state.value = State.ERROR
                Log.d("TAG", "SERVICE FAILED: ${t.message}")
            }
        })
    }


    //electronic
    fun getElectronicProducts() {

        state.value = State.LOADING

        StoreApi.service.getElectronicProduct().enqueue(object : Callback<List<Product>?> {
            override fun onResponse(call: Call<List<Product>?>, response: Response<List<Product>?>) {

                if(response.isSuccessful){
                    response.body()?.let { productList ->
                        Log.d("TAG", "SERVICE SUCCESS")
                        state.value = State.COMPLETED
                        products.postValue(productList)
                    }?: kotlin.run {
                        state.value = State.ERROR
                        Log.d("TAG", "EMPTY BODY")
                    }
                } else{
                    state.value = State.ERROR
                    Log.d("TAG", "SERVICE FAILED: ${response.message()}")
                }

            }

            override fun onFailure(call: Call<List<Product>?>, t: Throwable) {
                state.value = State.ERROR
                Log.d("TAG", "SERVICE FAILED: ${t.message}")
            }
        })
    }


    //men
    fun getMenProducts() {

        state.value = State.LOADING

        StoreApi.service.getMenProduct().enqueue(object : Callback<List<Product>?> {
            override fun onResponse(call: Call<List<Product>?>, response: Response<List<Product>?>) {

                if(response.isSuccessful){
                    response.body()?.let { productList ->
                        Log.d("TAG", "SERVICE SUCCESS")
                        state.value = State.COMPLETED
                        products.postValue(productList)
                    }?: kotlin.run {
                        state.value = State.ERROR
                        Log.d("TAG", "EMPTY BODY")
                    }
                } else{
                    state.value = State.ERROR
                    Log.d("TAG", "SERVICE FAILED: ${response.message()}")
                }

            }

            override fun onFailure(call: Call<List<Product>?>, t: Throwable) {
                state.value = State.ERROR
                Log.d("TAG", "SERVICE FAILED: ${t.message}")
            }
        })
    }


    //electronic
    fun getWomenProducts() {

        state.value = State.LOADING

        StoreApi.service.getWomenProduct().enqueue(object : Callback<List<Product>?> {
            override fun onResponse(call: Call<List<Product>?>, response: Response<List<Product>?>) {

                if(response.isSuccessful){
                    response.body()?.let { productList ->
                        Log.d("TAG", "SERVICE SUCCESS")
                        state.value = State.COMPLETED
                        products.postValue(productList)
                    }?: kotlin.run {
                        state.value = State.ERROR
                        Log.d("TAG", "EMPTY BODY")
                    }
                } else{
                    state.value = State.ERROR
                    Log.d("TAG", "SERVICE FAILED: ${response.message()}")
                }

            }

            override fun onFailure(call: Call<List<Product>?>, t: Throwable) {
                state.value = State.ERROR
                Log.d("TAG", "SERVICE FAILED: ${t.message}")
            }
        })
    }







}