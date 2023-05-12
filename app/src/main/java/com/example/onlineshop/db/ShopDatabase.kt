package com.example.onlineshop.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.onlineshop.model.CartItem
import com.example.onlineshop.model.FavoritesItem
import com.example.onlineshop.model.Product
import com.example.onlineshop.model.Rating
import com.google.gson.Gson

@Database(
    entities = [FavoritesItem::class, CartItem::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)

abstract class ShopDatabase: RoomDatabase(){
    abstract fun getDao(): ShopDao

    companion object{
        @Volatile
        private var instance: ShopDatabase? = null

        fun getDatabaseInstance(context: Context, gson: Gson): ShopDatabase{
            return instance ?: synchronized(this){
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    ShopDatabase::class.java,
                    "ShopDatabase"
                ).addTypeConverter(Converters(gson)).build()
                instance = newInstance
                newInstance
            }
        }
    }
}

