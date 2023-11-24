package com.example.hotelapp.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hotelapp.domain.entity.hotel.HotelEntity
import com.example.hotelapp.domain.usecase.GetHotelInfoUseCase
import javax.inject.Inject

class HotelViewModel @Inject constructor(
    private val getHotelInfoUseCase: GetHotelInfoUseCase
    ) : ViewModel() {
    private val _hotelInfo = getHotelInfoUseCase.invoke()
    val hotelInfo: LiveData<HotelEntity>
        get() = _hotelInfo
}