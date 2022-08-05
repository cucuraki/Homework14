package com.example.homework14

import com.squareup.moshi.Json


data class Data(val content: List<Content>) {
    data class Content(
        val id: Int,
        val descriptionEN: String,
        val descriptionKA: String,
        val descriptionRU: String,
        val titleEN: String,
        val titleKA: String,
        val titleRU: String,
        val published: Int,
        @Json(name = "publish_date")
        val publishDate: String,
        @Json(name = "created_at")
        val createdAt: Long,
        @Json(name = "updated_at")
        val updatedAt: Long,
        val category: String,
        val cover: String,
        val isLast: Boolean
        )
}
