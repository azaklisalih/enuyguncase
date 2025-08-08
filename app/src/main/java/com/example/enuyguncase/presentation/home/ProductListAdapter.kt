package com.example.enuyguncase.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.enuyguncase.databinding.ItemProductBinding
import com.example.enuyguncase.databinding.ItemProductShimmerBinding
import com.example.enuyguncase.domain.model.Product

class ProductListAdapter(
    private val onItemClick: (Product) -> Unit
) : ListAdapter<Any, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    private var isLoading = false

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        val item = getItem(position)
        return if (item is Product) item.id.toLong() else position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) is Product) VIEW_TYPE_PRODUCT else VIEW_TYPE_SHIMMER
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_PRODUCT -> {
                val binding = ItemProductBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                ProductViewHolder(binding)
            }
            VIEW_TYPE_SHIMMER -> {
                val binding = ItemProductShimmerBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                ShimmerViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ProductViewHolder -> {
                val product = getItem(position) as Product
                holder.bind(product)
            }
            is ShimmerViewHolder -> {
                // Shimmer effect is handled by the layout
            }
        }
    }

    fun setLoading(loading: Boolean) {
        if (isLoading != loading) {
            isLoading = loading
            if (loading) {
                val shimmerItems = List(10) { ShimmerItem() }
                submitList(shimmerItems)
            }
        }
    }

    fun submitProducts(products: List<Product>) {
        if (!isLoading) {
            submitList(products)
        }
    }
    
    fun forceRefresh(products: List<Product>) {
        if (!isLoading) {
            println("DEBUG: Adapter forceRefresh - Products count: ${products.size}")
            println("DEBUG: Adapter forceRefresh - First product: ${products.firstOrNull()?.title}")
            submitList(null)
            submitList(products)
        }
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

    inner class ShimmerViewHolder(
        private val binding: ItemProductShimmerBinding
    ) : RecyclerView.ViewHolder(binding.root)

    private data class ShimmerItem(val id: Int = 0)

    companion object {
        private const val VIEW_TYPE_PRODUCT = 0
        private const val VIEW_TYPE_SHIMMER = 1

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Any>() {
            override fun areItemsTheSame(old: Any, new: Any): Boolean {
                return when {
                    old is Product && new is Product -> old.id == new.id
                    old is ShimmerItem && new is ShimmerItem -> old.id == new.id
                    else -> false
                }
            }

            override fun areContentsTheSame(old: Any, new: Any): Boolean {
                return when {
                    old is Product && new is Product -> old.id == new.id
                    old is ShimmerItem && new is ShimmerItem -> old.id == new.id
                    else -> false
                }
            }
        }
    }
}
