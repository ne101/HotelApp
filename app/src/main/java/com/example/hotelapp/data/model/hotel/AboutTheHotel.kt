package com.example.hotelapp.data.model.hotel

import com.google.gson.annotations.SerializedName

data class AboutTheHotel(
    @SerializedName("description")
    val description: String,
    @SerializedName("peculiarities")
    val peculiarities: List<String>
)