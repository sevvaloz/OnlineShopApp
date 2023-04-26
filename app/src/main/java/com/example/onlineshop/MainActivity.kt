package com.example.onlineshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.onlineshop.R.layout.activity_main
import com.example.onlineshop.adapter.ProductAdapter
import com.example.onlineshop.viewmodel.MainViewModel
import com.example.onlineshop.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewmodel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        //hide the application action bar
        supportActionBar?.hide()

        dataBinding()
        sendRequest()
        listeners()
        observeViewModel()

    }

    fun dataBinding(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun sendRequest(){
        viewmodel.getProducts()
    }

    private fun listeners(){
        binding.btnJewelery.setOnClickListener {
            viewmodel.getJewelery()
        }
        binding.btnElectronic.setOnClickListener {
            viewmodel.getElectronic()
        }
        binding.btnMen.setOnClickListener {
            viewmodel.getMen()
        }
        binding.btnWomen.setOnClickListener {
            viewmodel.getWomen()
        }
        binding.btnAll.setOnClickListener {
            viewmodel.getProducts()
        }
    }

    private fun observeViewModel(){
        viewmodel.productsData.observe(this){ productList ->

            //recyclerview
            binding.productRecyclerview.adapter = ProductAdapter(productList)
            binding.productRecyclerview.layoutManager =  GridLayoutManager(this@MainActivity, 2)

        }
    }



}