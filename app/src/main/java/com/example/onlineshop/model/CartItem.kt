package com.example.onlineshop.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CartTable")
data class CartItem(
    @PrimaryKey
    val id: Int,
    val image: String,
    val price: Double,
    val quantity: Int
): java.io.Serializable
