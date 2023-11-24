package com.example.hotelapp.data.model.booking

import com.google.gson.annotations.SerializedName

data class BookingResponse(
    @SerializedName("arrival_country")
    val arrival_country: String,
    @SerializedName("departure")
    val departure: String,
    @SerializedName("fuel_charge")
    val fuel_charge: Int,
    @SerializedName("horating")
    val horating: Int,
    @SerializedName("hotel_adress")
    val hotel_adress: String,
    @SerializedName("hotel_name")
    val hotel_name: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("number_of_nights")
    val number_of_nights: Int,
    @SerializedName("nutrition")
    val nutrition: String,
    @SerializedName("rating_name")
    val rating_name: String,
    @SerializedName("room")
    val room: String,
    @SerializedName("service_charge")
    val service_charge: Int,
    @SerializedName("tour_date_start")
    val tour_date_start: String,
    @SerializedName("tour_date_stop")
    val tour_date_stop: String,
    @SerializedName("tour_price")
    val tour_price: Int
)