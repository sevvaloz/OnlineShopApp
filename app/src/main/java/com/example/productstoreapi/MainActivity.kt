package com.example.productstoreapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.productstoreapi.R.layout.activity_main
import com.example.productstoreapi.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewmodel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        //hide the application action bar
        supportActionBar?.hide()

        bind()
        sendRequest()
        observeViewModel()

    }

    private fun bind(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun sendRequest(){
        //service request
        viewmodel.getProducts()

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
            binding.productRecyclerview.adapter = Adapter(productList)
            binding.productRecyclerview.layoutManager =  GridLayoutManager(this@MainActivity, 2)

        }
    }



}