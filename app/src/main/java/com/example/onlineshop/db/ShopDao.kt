package com.example.onlineshop.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.onlineshop.model.FavoritesItem
import com.example.onlineshop.model.Product

@Dao
interface ShopDao {

    @Query("SELECT * FROM FavoritesTable")
    fun getAllFavorites(): LiveData<List<FavoritesItem>>

    @Insert
    fun addToFavorites(favItem: FavoritesItem)

    @Delete
    fun deleteFromFavorites(favItem: FavoritesItem)
}