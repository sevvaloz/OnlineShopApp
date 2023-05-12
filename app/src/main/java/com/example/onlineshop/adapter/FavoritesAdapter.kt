package com.example.onlineshop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshop.databinding.ItemProductBinding
import com.example.onlineshop.model.Product

class FavoritesAdapter(private val favoritesList: List<Product>,
                       //private val favClickListener: RowClick<FavoritesItem>
): RecyclerView.Adapter<FavoritesAdapter.FavoriteProductViewHolder>() {

    class FavoriteProductViewHolder(val binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteProductViewHolder {
        return FavoriteProductViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = favoritesList.size

    override fun onBindViewHolder(holder: FavoriteProductViewHolder, position: Int) {
        holder.binding.apply {
            val favoriteItem = favoritesList[position]
            title.text = favoriteItem.title
            price.text = favoriteItem.price.toString()
            Glide.with(holder.binding.root).load(favoriteItem.image).into(img)

            /*favButton.setOnClickListener {
                favClickListener.onRowClick(position, favoriteItem)
            }*/

        }
    }

}