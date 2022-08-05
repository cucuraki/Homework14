package com.example.homework14


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework14.databinding.ItemLayoutBinding


class Adapter : ListAdapter<Data.Content, Adapter.ContentViewHolder>(ContentDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ContentViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context)))


    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind()
    }

    inner class ContentViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val cover = binding.cover
        private val title = binding.tittle
        private val description = binding.description
        private val date = binding.time
        fun bind() {
            val item = getItem(adapterPosition)
            item.apply {
                title.text = titleKA
                description.text = descriptionKA
                date.text = publishDate
                Glide.with(binding.root.context)
                    .load(cover)
                    .into(this@ContentViewHolder.cover)
            }
        }
    }
}