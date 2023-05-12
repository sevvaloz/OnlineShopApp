package com.example.onlineshop.db

import androidx.lifecycle.LiveData
import com.example.onlineshop.model.Product

class ShopRepository(private val dao: ShopDao) {

    fun addToFavorites(favItem: Product){
        dao.addToFavorites(favItem)
    }

    fun deleteFromFavorites(favItem: Product) {
        dao.deleteFromFavorites(favItem)
    }

    val allFavorites: LiveData<List<Product>> = dao.getAllFavorites()
}