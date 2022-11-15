package com.hampson.asta.presentation.register_auction

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hampson.asta.databinding.ItemRegisterProductBinding

class ImagesPickAdapter(
    private var imageList: ArrayList<Uri>?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onItemClickListener: ((Uri) -> Unit)? = null
    fun setOnItemClickListener(listener: (Uri) -> Unit) {
        onItemClickListener = listener
    }

    var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MyViewHolder(
            ItemRegisterProductBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyViewHolder).apply {
            bindItem(imageList?.get(position))
        }
    }

    override fun getItemCount(): Int {
        return imageList?.size ?: 0
    }

    inner class MyViewHolder(val binding: ItemRegisterProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: Uri?) {

            Glide
                .with(context!!)
                .load(item)
                .centerCrop()
                .into(binding.imageViewProduct)

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    if (item != null) it(item)
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun replaceImages(imageList: ArrayList<Uri>?) {
        this.imageList = arrayListOf()
        this.imageList = imageList
        notifyDataSetChanged()
    }

    fun removeImage(image: Uri) {
        notifyItemRemoved(imageList?.indexOf(image) ?: 0)
        imageList?.remove(image)
    }
}