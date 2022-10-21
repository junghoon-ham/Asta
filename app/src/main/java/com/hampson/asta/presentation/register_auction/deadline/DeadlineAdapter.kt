package com.hampson.asta.presentation.register_auction.deadline

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hampson.asta.databinding.ItemListBinding

class DeadlineAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataList = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    private var onItemClickListener: ((Int) -> Unit)? = null
    fun setOnItemClickListener(listener: (Int) -> Unit) {
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
        fun bindItem(item: Int) {
            binding.textViewTitle.text = "${item}일 후"

            binding.root.setOnClickListener {
                onItemClickListener?.let { it(item) }
            }
        }
    }
}