package com.example.onlineshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.onlineshop.adapter.FavoritesAdapter
import com.example.onlineshop.databinding.ActivityFavoriteProductsBinding
import com.example.onlineshop.viewmodel.MainViewModel

class FavoriteProductsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteProductsBinding
    val viewmodel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_products)

        supportActionBar?.title = "Favorites"

        viewmodel.init(application)

        viewBinding()
        activityTransitions()
        observeViewmodel()

    }

    private fun viewBinding(){
        binding = ActivityFavoriteProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun observeViewmodel(){
        viewmodel.allFavorites?.observe(this){ favList ->
            //fav recyclerview
            binding.favoritesRecyclerview.adapter = FavoritesAdapter(favList)
            binding.favoritesRecyclerview.layoutManager =  GridLayoutManager(this@FavoriteProductsActivity, 2)
        }

        Log.d("AGA", viewmodel.allFavorites.toString())
    }

    private fun activityTransitions(){
        binding.bottomNavigation.selectedItemId = R.id.bottom_favorite
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.bottom_favorite -> return@setOnItemSelectedListener true
                R.id.bottom_cart -> {
                    startActivity(Intent(applicationContext, CartActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    return@setOnItemSelectedListener true
                }
                R.id.bottom_home -> {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
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