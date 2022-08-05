package com.example.homework14

import androidx.recyclerview.widget.DiffUtil

class ContentDiffUtil : DiffUtil.ItemCallback<Data.Content>() {
    override fun areItemsTheSame(oldItem: Data.Content, newItem: Data.Content) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Data.Content, newItem: Data.Content) =
        (oldItem.cover == newItem.cover && oldItem.titleKA == newItem.titleKA && oldItem.descriptionKA == newItem.descriptionKA)
}
