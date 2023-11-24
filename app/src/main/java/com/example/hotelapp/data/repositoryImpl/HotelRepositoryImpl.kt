package com.example.hotelapp.data.repositoryImpl

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.hotelapp.data.api.ApiService
import com.example.hotelapp.data.mapper.HotelMapper
import com.example.hotelapp.domain.entity.booking.BookingEntity
import com.example.hotelapp.domain.entity.hotel.HotelEntity
import com.example.hotelapp.domain.entity.room.RoomListEntity
import com.example.hotelapp.domain.repository.HotelRepository
import javax.inject.Inject

class HotelRepositoryImpl @Inject constructor(
    private val mapper: HotelMapper,
    private val apiService: ApiService
) : HotelRepository {

    override fun getHotelInfo(): LiveData<HotelEntity> {
        return liveData {
            val response = apiService.loadInfoAboutHotel()
            emit(mapper.mapHotelResponseToHotelEntity(response))
        }
    }

    override fun getRooms(): LiveData<RoomListEntity> {
        return liveData {
            val response = apiService.loadRoomLisl()
            emit(mapper.mapRoomResponseToRoomListEntity(response))
        }
    }

    override fun getBookingInfo(): LiveData<BookingEntity> {
        return liveData {
            val response = apiService.loadBooking()
            emit(mapper.mapBookingResponseToBookingEntity(response))
        }
    }
}