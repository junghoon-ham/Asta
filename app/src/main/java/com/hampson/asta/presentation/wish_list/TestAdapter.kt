package com.hampson.asta.presentation.wish_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.hampson.asta.databinding.ItemProductBinding
import com.hampson.asta.domain.model.Product
import java.text.DecimalFormat

class TestAdapter(val dataList: ArrayList<Product> = ArrayList()) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val dec = DecimalFormat("#,###")

    private var onItemClickListener: ((Product) -> Unit)? = null
    fun setOnItemClickListener(listener: (Product) -> Unit) {
        onItemClickListener = listener
    }

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

    inner class MyViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: Product) {
            binding.textViewTitle.text = item.productName
            binding.textViewBidder.text = "${item.bidderCount}명"
            binding.textViewCurrentPrice.text = "${dec.format(item.currentPrice)}원"
            binding.textViewStartPrice.text = "${dec.format(item.startPrice)}원"

            binding.imageViewProfile.setImageResource(item.productMainImage!!)

            binding.includeStatus.root.isVisible = true

            binding.root.setOnClickListener {
                onItemClickListener?.let { it(item) }
            }
        }


    }
}