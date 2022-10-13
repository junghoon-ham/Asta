package com.hampson.asta.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.hampson.asta.databinding.ItemProductBinding
import com.hampson.asta.domain.model.Product

class AuctionPagingAdapter : PagingDataAdapter<Product, AuctionViewHolder>(ProductDiffCallback) {

    override fun onBindViewHolder(holder: AuctionViewHolder, position: Int) {
        val pagedBook = getItem(position)
        pagedBook?.let { product ->
            holder.bind(product)
            holder.itemView.setOnClickListener {
                onItemClickListener?.let { it(product) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuctionViewHolder {
        return AuctionViewHolder(
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    private var onItemClickListener: ((Product) -> Unit)? = null
    fun setOnItemClickListener(listener: (Product) -> Unit) {
        onItemClickListener = listener
    }

    companion object {
        private val ProductDiffCallback = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }
        }
    }
}