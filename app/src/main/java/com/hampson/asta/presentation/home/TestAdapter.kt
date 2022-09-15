package com.hampson.asta.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hampson.asta.databinding.ItemProductBinding
import com.hampson.asta.domain.model.Product

class TestAdapter(val dataList: ArrayList<Product> = ArrayList()): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MyViewHolder(ItemProductBinding.inflate(LayoutInflater.from(context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyViewHolder).apply {
            bindItem(dataList[position])
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class MyViewHolder(val binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: Product) {
            binding.textViewTitle.text = item.productName
            binding.textViewBidder.text = "${item.bidderCount}명"
            binding.textViewCurrentPrice.text = "${item.currentPrice}원"
            binding.textViewStartPrice.text = "${item.startPrice}원"
        }
    }
}