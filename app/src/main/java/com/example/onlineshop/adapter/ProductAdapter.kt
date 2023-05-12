package com.example.onlineshop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshop.databinding.ItemProductBinding
import com.example.onlineshop.model.FavoritesItem
import com.example.onlineshop.model.Product
import com.example.onlineshop.utils.RowClick


class ProductAdapter(private val productList: List<Product>,
                     private val favClickListener: RowClick<Product>
): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

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

            favButton.setOnClickListener {
                favClickListener.onRowClick(position, product)
            }
        }
    }


}