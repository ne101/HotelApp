package com.example.hotelapp.domain.usecase

import androidx.lifecycle.LiveData
import com.example.hotelapp.domain.entity.booking.BookingEntity
import com.example.hotelapp.domain.repository.HotelRepository
import javax.inject.Inject

class GetBookingInfoUseCase @Inject constructor(private val hotelRepository: HotelRepository) {
    operator fun invoke(): LiveData<BookingEntity> {
        return hotelRepository.getBookingInfo()
    }
}