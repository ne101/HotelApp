package com.example.hotelapp.domain.usecase

import androidx.lifecycle.LiveData
import com.example.hotelapp.domain.entity.room.RoomListEntity
import com.example.hotelapp.domain.repository.HotelRepository
import javax.inject.Inject

class GetRoomsUseCase @Inject constructor(private val hotelRepository: HotelRepository) {
    operator fun invoke(): LiveData<RoomListEntity> {
        return hotelRepository.getRooms()
    }
}