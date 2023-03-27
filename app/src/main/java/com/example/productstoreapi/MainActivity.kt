package com.example.productstoreapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.productstoreapi.databinding.ActivityMainBinding
import com.example.productstoreapi.network.State


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewmodel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //hide action bar
        supportActionBar?.hide()

        bind()
        sendRequest()
        observeViewModel()

    }

    private fun bind(){
        //binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun sendRequest(){
        //service request
        viewmodel.getProducts()

        binding.btnJewelery.setOnClickListener {
            viewmodel.getJeweleryProducts()
        }
        binding.btnElectronic.setOnClickListener {
            viewmodel.getElectronicProducts()
        }
       binding.btnMen.setOnClickListener {
            viewmodel.getMenProducts()
        }
        binding.btnWomen.setOnClickListener {
            viewmodel.getWomenProducts()
        }
        binding.btnAll.setOnClickListener {
            viewmodel.getProducts()
        }
    }

    private fun observeViewModel(){
        viewmodel.productsData.observe(this){ productList ->

            //recyclerview
            binding.productRecyclerview.adapter = Adapter(productList)
            binding.productRecyclerview.layoutManager =  GridLayoutManager(this@MainActivity, 2)


            //states
            viewmodel.stateData.observe(this) { currentState ->
                when(currentState) {
                    State.LOADING -> {
                        Log.d("TAG", "Loading")
                    }
                    State.COMPLETED -> {
                        Log.d("TAG", "Completed")
                    }
                    State.ERROR -> {
                        Log.d("TAG", "Error")
                    }
                    null -> {
                        Log.d("TAG", "Null")
                    }
                }
            }
        }

   /*     viewmodel.productsData.observe(this){ productList ->

            //recyclerview
            binding.productRecyclerview.adapter = Adapter(productList)
            binding.productRecyclerview.layoutManager =  GridLayoutManager(this@MainActivity, 2)

            //states
            viewmodel.stateData.observe(this) { currentState ->
                when(currentState) {
                    State.LOADING -> {
                        Log.d("TAG", "Loading")
                    }
                    State.COMPLETED -> {
                        Log.d("TAG", "Completed")
                    }
                    State.ERROR -> {
                        Log.d("TAG", "Error")
                    }
                    null -> {
                        Log.d("TAG", "Null")
                    }
                }
            }

        }*/

    }



}