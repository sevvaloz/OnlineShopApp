package com.example.productstoreapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.productstoreapi.databinding.ItemProductBinding
import com.example.productstoreapi.network.response.Product

class Adapter(private val productList: List<Product>): RecyclerView.Adapter<Adapter.ProductViewHolder>() {

    class ProductViewHolder(val binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.binding.apply {
            val product = productList[position]
            title.text = product.title
            price.text = product.price.toString()
            Glide.with(holder.binding.root).load(product.image).into(img)
        }
    }


}