package com.example.onlineshop.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.onlineshop.model.Rating
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(private val gson: Gson) {
    @TypeConverter
    fun fromRating(rating: Rating): String {
        return gson.toJson(rating)
    }

    @TypeConverter
    fun toRating(json: String): Rating {
        val type = object : TypeToken<Rating>() {}.type
        return gson.fromJson(json, type)
    }
}