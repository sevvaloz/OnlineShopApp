package com.example.onlineshop.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.onlineshop.model.Product
import com.example.onlineshop.utils.Constant.Companion.FavoritesTable

@Dao
interface ShopDao {
    @Query("SELECT * FROM $FavoritesTable")
    fun getAllFavorites(): LiveData<List<Product>>

    @Insert
    fun addToFavorites(favItem: Product)

    @Delete
    fun deleteFromFavorites(favItem: Product)
}