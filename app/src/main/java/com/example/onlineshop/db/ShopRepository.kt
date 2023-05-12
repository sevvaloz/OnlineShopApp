package com.example.onlineshop.db

import androidx.lifecycle.LiveData
import com.example.onlineshop.model.FavoritesItem

class ShopRepository(private val dao: ShopDao) {

    fun addToFavorites(favItem: FavoritesItem){
        dao.addToFavorites(favItem)
    }

    fun deleteFromFavorites(favItem: FavoritesItem) {
        dao.deleteFromFavorites(favItem)
    }

    val allFavorites: LiveData<List<FavoritesItem>> = dao.getAllFavorites()
}