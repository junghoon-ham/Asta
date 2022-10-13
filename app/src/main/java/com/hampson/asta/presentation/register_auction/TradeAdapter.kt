package com.hampson.asta.presentation.register_auction

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hampson.asta.databinding.ItemListBinding
import com.hampson.asta.domain.util.TradeType

class TradeAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataList = enumValues<TradeType>()

    private var onItemClickListener: ((TradeType) -> Unit)? = null
    fun setOnItemClickListener(listener: (TradeType) -> Unit) {
        onItemClickListener = listener
    }

    var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MyViewHolder(
            ItemListBinding.inflate(
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

    inner class MyViewHolder(val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: TradeType) {
            binding.textViewTitle.text = item.tradeName

            binding.root.setOnClickListener {
                onItemClickListener?.let { it(item) }
            }
        }
    }
}