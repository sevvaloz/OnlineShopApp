package com.example.onlineshop

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.onlineshop.R.layout.activity_main
import com.example.onlineshop.adapter.ProductAdapter
import com.example.onlineshop.databinding.ActivityMainBinding
import com.example.onlineshop.model.Product
import com.example.onlineshop.utils.RowClick
import com.example.onlineshop.viewmodel.MainViewModel


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val viewmodel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private var inCartsList: ArrayList<Product> = arrayListOf()
    private var favoriteProductList: ArrayList<Product> = arrayListOf()

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
        println(viewmodel.getProducts())
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

    private val favoritesListener = object : RowClick<Product> {
        override fun onRowClick(pos: Int, item: Product) {
            Log.d("TAG", item.title + " tiklandi")
            Intent(this@MainActivity, FavoriteProductsActivity::class.java).putExtra("favItem", item)
        }
    }

    private fun observeViewModel(){
        viewmodel.productsData.observe(this){ productList ->
            //main recyclerview
            binding.productRecyclerview.adapter = ProductAdapter(productList, favoritesListener)
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
            searchProduct(query)
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if(query != null){
            searchProduct(query)
        }
        return true
    }

    private fun searchProduct(query: String){
        viewmodel.productsData.observe(this){ productList ->
            for (product in productList){
                if(product.title.contains(query)){

                }
            }
        }
    }

    private fun showAlertDialog() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle("Sort by")
        val items = arrayOf("Increasing Price", "Decreasing Price")
        alertDialog.setItems(items) { dialog, which ->
            when (which) {
                0 -> viewmodel.getProducts()
                1 -> viewmodel.getProducts()
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