package com.example.hotelapp.data.model.room

import com.google.gson.annotations.SerializedName

data class RoomResponse(
    @SerializedName("rooms")
    val rooms: List<Room>
)