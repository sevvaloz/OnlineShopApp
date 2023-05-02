package com.example.onlineshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onlineshop.databinding.ActivityCartBinding
import com.example.onlineshop.databinding.ActivityMainBinding

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        supportActionBar?.title = "Shopping Cart"

        viewBinding()
        activityTransitions()

    }

    fun viewBinding(){
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun activityTransitions(){
        binding.bottomNavigation.selectedItemId = R.id.bottom_cart
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.bottom_cart -> return@setOnItemSelectedListener true
                R.id.bottom_favorite -> {
                    startActivity(Intent(applicationContext, FavoriteProductsActivity::class.java))
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