package com.example.hotelapp.di

import com.example.hotelapp.presentation.fragments.BookingFragment
import com.example.hotelapp.presentation.fragments.HotelFragment
import com.example.hotelapp.presentation.fragments.RoomsFragment
import dagger.Component

@ApplicationScope
@Component(modules = [
    DataModule::class,
    ViewModelModule::class
])
interface ApplicationComponent {

    fun inject(fragment: HotelFragment)

    fun inject(fragment: RoomsFragment)

    fun inject(fragment: BookingFragment)
}