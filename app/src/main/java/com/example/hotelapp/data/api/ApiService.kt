package com.example.hotelapp.data.api

import com.example.hotelapp.data.model.booking.BookingResponse
import com.example.hotelapp.data.model.hotel.HotelResponse
import com.example.hotelapp.data.model.room.RoomResponse
import retrofit2.http.GET

interface ApiService {
    @GET("d144777c-a67f-4e35-867a-cacc3b827473")
    suspend fun loadInfoAboutHotel(): HotelResponse

    @GET("8b532701-709e-4194-a41c-1a903af00195")
    suspend fun loadRoomLisl(): RoomResponse

    @GET("63866c74-d593-432c-af8e-f279d1a8d2ff")
    suspend fun loadBooking(): BookingResponse
}