package com.hampson.asta.presentation.category

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hampson.asta.databinding.ItemCategoryBinding
import com.hampson.asta.domain.util.CategoryType

class CategoryAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataList = enumValues<CategoryType>()

    private var onItemClickListener: ((CategoryType) -> Unit)? = null
    fun setOnItemClickListener(listener: (CategoryType) -> Unit) {
        onItemClickListener = listener
    }

    var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MyViewHolder(
            ItemCategoryBinding.inflate(
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

    inner class MyViewHolder(val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: CategoryType) {
            binding.textViewCategoryTitle.text = item.categoryName(context)
            binding.textViewCategoryImage.setImageResource(item.categoryImage())

            binding.root.setOnClickListener {
                onItemClickListener?.let { it(item) }
            }
        }
    }
}