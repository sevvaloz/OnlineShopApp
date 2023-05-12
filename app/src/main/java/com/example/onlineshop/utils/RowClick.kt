package com.example.onlineshop.utils

interface RowClick<T> {
    fun onRowClick(pos: Int, item: T)
}