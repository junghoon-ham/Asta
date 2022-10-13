package com.hampson.asta.presentation.comment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hampson.asta.databinding.ItemCommentBinding
import com.hampson.asta.domain.model.Comment
import java.text.DecimalFormat

class TestAdapter(val dataList: ArrayList<Comment> = ArrayList()) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val dec = DecimalFormat("#,###")

    private var onItemClickListener: ((Comment) -> Unit)? = null
    fun setOnItemClickListener(listener: (Comment) -> Unit) {
        onItemClickListener = listener
    }

    var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MyViewHolder(
            ItemCommentBinding.inflate(
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

    inner class MyViewHolder(val binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: Comment) {
            binding.textViewName.text = item.userName
            binding.textViewComment.text = item.comment

            binding.root.setOnClickListener {
                onItemClickListener?.let { it(item) }
            }
        }
    }
}