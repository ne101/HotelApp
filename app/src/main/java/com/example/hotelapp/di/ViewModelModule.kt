package com.example.hotelapp.di

import androidx.lifecycle.ViewModel
import com.example.hotelapp.presentation.viewModels.BookingViewModel
import com.example.hotelapp.presentation.viewModels.HotelViewModel
import com.example.hotelapp.presentation.viewModels.RoomsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HotelViewModel::class)
    fun bindHotelViewModel(viewModel: HotelViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RoomsViewModel::class)
    fun bindRoomsViewModel(viewModel: RoomsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BookingViewModel::class)
    fun bindBookingViewModel(viewModel: BookingViewModel): ViewModel
}