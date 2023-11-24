package com.example.hotelapp.presentation

import android.app.Application
import com.example.hotelapp.di.DaggerApplicationComponent

class HotelApp: Application() {
    val component by lazy {
        DaggerApplicationComponent.create()
    }
}