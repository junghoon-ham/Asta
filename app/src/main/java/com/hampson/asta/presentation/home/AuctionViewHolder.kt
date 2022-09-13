package com.hampson.asta.presentation.home

import androidx.recyclerview.widget.RecyclerView
import com.hampson.asta.databinding.ItemProductBinding
import com.hampson.asta.domain.model.Product

class AuctionViewHolder(
    private val binding: ItemProductBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: Product) {

        itemView.apply {
            binding.textViewTitle.text = product.productName
            binding.textViewBidder.text = "${product.bidderCount}명"
            binding.textViewCurrentPrice.text = "320,000원"
            binding.textViewStartPrice.text = "200,000원"
        }
    }
}