package com.example.hotelapp.data.model.room

import com.google.gson.annotations.SerializedName

data class Room(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_urls")
    val image_urls: List<String>,
    @SerializedName("name")
    val name: String,
    @SerializedName("peculiarities")
    val peculiarities: List<String>,
    @SerializedName("price")
    val price: Int,
    @SerializedName("price_per")
    val price_per: String
)