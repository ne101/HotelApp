package com.example.hotelapp.data.model.hotel

import com.google.gson.annotations.SerializedName

data class HotelResponse(
    @SerializedName("about_the_hotel")
    val about_the_hotel: AboutTheHotel,
    @SerializedName("adress")
    val adress: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_urls")
    val image_urls: List<String>,
    @SerializedName("minimal_price")
    val minimal_price: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price_for_it")
    val price_for_it: String,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("rating_name")
    val rating_name: String
)