package com.example.hotelapp.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hotelapp.domain.entity.room.RoomListEntity
import com.example.hotelapp.domain.usecase.GetRoomsUseCase
import javax.inject.Inject

class RoomsViewModel @Inject constructor(
    private val getRoomsUseCase: GetRoomsUseCase
): ViewModel() {
    private val _rooms = getRoomsUseCase.invoke()
    val rooms: LiveData<RoomListEntity>
        get() = _rooms
}