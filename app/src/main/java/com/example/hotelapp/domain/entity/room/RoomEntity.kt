package com.example.hotelapp.domain.entity.room

data class RoomEntity(
    val id: Int,
    val image_urls: List<String>,
    val name: String,
    val peculiarities: List<String>,
    val price: Int,
    val price_per: String
)
