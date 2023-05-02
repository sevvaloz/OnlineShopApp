package com.example.onlineshop

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.onlineshop.R.layout.activity_main
import com.example.onlineshop.adapter.ProductAdapter
import com.example.onlineshop.databinding.ActivityMainBinding
import com.example.onlineshop.viewmodel.MainViewModel


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private val viewmodel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        supportActionBar?.title = "Welcome"

        viewBinding()
        sendRequest()
        listeners()
        observeViewModel()
        activityTransitions()

    }

    fun viewBinding(){
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
        binding.sortButton.setOnClickListener {
            showAlertDialog()
        }
    }

    private fun observeViewModel(){
        viewmodel.productsData.observe(this){ productList ->

            //recyclerview
            binding.productRecyclerview.adapter = ProductAdapter(productList)
            binding.productRecyclerview.layoutManager =  GridLayoutManager(this@MainActivity, 2)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this@MainActivity)

        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query != null){

        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if(query != null){

        }
        return true
    }

    private fun showAlertDialog() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle("Sort by")
        val items = arrayOf("Increasing Price", "Decreasing Price")
        alertDialog.setItems(items) { dialog, which ->
            when (which) {
                0 -> Toast.makeText(this@MainActivity, "increased price", Toast.LENGTH_LONG).show()
                1 -> Toast.makeText(this@MainActivity, "decreasing price", Toast.LENGTH_LONG).show()
            }
        }
        val alert: AlertDialog = alertDialog.create()
        alert.show()
    }

    fun activityTransitions(){
        binding.bottomNavigation.selectedItemId = R.id.bottom_home
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.bottom_home -> return@setOnItemSelectedListener true
                R.id.bottom_favorite -> {
                    startActivity(Intent(applicationContext, FavoriteProductsActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    return@setOnItemSelectedListener true
                }
                R.id.bottom_cart -> {
                    startActivity(Intent(applicationContext, CartActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    return@setOnItemSelectedListener true
                }
                R.id.bottom_profile -> {
                    startActivity(Intent(applicationContext, ProfileActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }


}