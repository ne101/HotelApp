package com.example.hotelapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.hotelapp.data.model.booking.BookingResponse
import com.example.hotelapp.domain.entity.booking.BookingEntity
import com.example.hotelapp.domain.entity.hotel.HotelEntity
import com.example.hotelapp.domain.entity.room.RoomListEntity

interface HotelRepository {
    fun getHotelInfo(): LiveData<HotelEntity>
    fun getRooms(): LiveData<RoomListEntity>
    fun getBookingInfo(): LiveData<BookingEntity>
}