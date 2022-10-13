package com.hampson.asta.presentation.register_auction

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hampson.asta.databinding.ItemConditionBinding
import com.hampson.asta.domain.util.ConditionType

class ConditionAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataList = enumValues<ConditionType>()

    private var onItemClickListener: ((ConditionType) -> Unit)? = null
    fun setOnItemClickListener(listener: (ConditionType) -> Unit) {
        onItemClickListener = listener
    }

    var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MyViewHolder(
            ItemConditionBinding.inflate(
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

    inner class MyViewHolder(val binding: ItemConditionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: ConditionType) {
            binding.textViewCondition.text = item.conditionName
            binding.textViewExplanation.text = item.conditionExplanation()

            binding.root.setOnClickListener {
                onItemClickListener?.let { it(item) }
            }
        }
    }
}