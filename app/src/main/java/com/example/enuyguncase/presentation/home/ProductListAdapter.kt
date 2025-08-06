package com.example.enuyguncase.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.enuyguncase.databinding.ItemProductBinding
import com.example.enuyguncase.domain.model.Product

class ProductListAdapter(
    private val onItemClick: (Product) -> Unit
) : ListAdapter<Product, ProductListAdapter.ProductViewHolder>(DIFF_CALLBACK) {

    init {
        // Stable IDs, animasyonlar için
        setHasStableIds(true)
    }

    // ListAdapter.getItem(position) zaten Product döndürür
    override fun getItemId(position: Int): Long =
        getItem(position).id.toLong()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProductViewHolder(
        private val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.product = product
            binding.root.setOnClickListener { onItemClick(product) }
            binding.executePendingBindings()
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(old: Product, new: Product) =
                old.id == new.id

            override fun areContentsTheSame(old: Product, new: Product) =
                old == new
        }
    }
}
