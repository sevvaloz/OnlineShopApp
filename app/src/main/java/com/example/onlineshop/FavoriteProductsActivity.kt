package com.example.onlineshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onlineshop.databinding.ActivityFavoriteProductsBinding

class FavoriteProductsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteProductsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_products)

        supportActionBar?.title = "Favorites"

        viewBinding()
        activityTransitions()

    }

    fun viewBinding(){
        binding = ActivityFavoriteProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun activityTransitions(){
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