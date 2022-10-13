package com.hampson.asta.presentation.detail_auction.history_bid

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hampson.asta.R
import com.hampson.asta.databinding.ItemHistoryBidBinding
import com.hampson.asta.domain.model.Bid
import java.text.DecimalFormat

class TestAdapter(val dataList: ArrayList<Bid> = ArrayList()) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val dec = DecimalFormat("#,###")

    private var onItemClickListener: ((Bid) -> Unit)? = null
    fun setOnItemClickListener(listener: (Bid) -> Unit) {
        onItemClickListener = listener
    }

    var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MyViewHolder(
            ItemHistoryBidBinding.inflate(
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

    inner class MyViewHolder(val binding: ItemHistoryBidBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: Bid) {
            binding.textViewName.text = item.userName
            binding.textViewRank.text = (position + 1).toString()
            binding.textViewBidPrice.text =
                context?.getString(R.string.history_bid, dec.format(item.bid))

            binding.root.setOnClickListener {
                onItemClickListener?.let { it(item) }
            }
        }
    }
}