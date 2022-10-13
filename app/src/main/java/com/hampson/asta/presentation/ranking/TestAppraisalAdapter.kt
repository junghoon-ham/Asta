package com.hampson.asta.presentation.ranking

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hampson.asta.databinding.ItemRankingAppraisalBinding
import com.hampson.asta.domain.model.Product
import java.text.DecimalFormat

class TestAppraisalAdapter(val dataList: ArrayList<Product> = ArrayList()) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val dec = DecimalFormat("#,###")

    private var onItemClickListener: ((Product) -> Unit)? = null
    fun setOnItemClickListener(listener: (Product) -> Unit) {
        onItemClickListener = listener
    }

    var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MyViewHolder(
            ItemRankingAppraisalBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyViewHolder).apply {
            bindItem(dataList[position])
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class MyViewHolder(val binding: ItemRankingAppraisalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: Product) {
            binding.textViewRank.text = (position + 1).toString()
            binding.textViewPrice.text = "${dec.format(item.currentPrice)}원"
            binding.textViewProductName.text = item.productName

            binding.imageViewProduct.setImageResource(item.productMainImage!!)

            binding.root.setOnClickListener {
                onItemClickListener?.let { it(item) }
            }
        }
    }
}