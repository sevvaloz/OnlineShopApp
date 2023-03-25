package com.example.productstoreapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productstoreapi.databinding.ActivityMainBinding
import com.example.productstoreapi.network.StoreApi
import com.example.productstoreapi.network.response.Product
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //recyclerview
        getList { binding.productRecyclerview.adapter = Adapter(it) }
        binding.productRecyclerview.layoutManager = LinearLayoutManager(this@MainActivity)

    }


    //retrofit
    private fun getList(callback: (List<Product>) -> Unit) {

        StoreApi.retrofit.getAllProducts().enqueue(object : Callback<List<Product>?> {
            override fun onResponse(call: Call<List<Product>?>, response: Response<List<Product>?>) {
                //response.body()
                return callback(response.body()!!)
            }

            override fun onFailure(call: Call<List<Product>?>, t: Throwable) {
                Log.e("TAG", "on fail")
            }
        })
    }

}