package com.example.hotelapp.domain.usecase

import androidx.lifecycle.LiveData
import com.example.hotelapp.domain.entity.hotel.HotelEntity
import com.example.hotelapp.domain.repository.HotelRepository
import javax.inject.Inject

class GetHotelInfoUseCase @Inject constructor(private val hotelRepository: HotelRepository) {
    operator fun invoke(): LiveData<HotelEntity> {
        return hotelRepository.getHotelInfo()
    }
}