package com.example.hotelapp.di

import com.example.hotelapp.data.api.ApiFactory
import com.example.hotelapp.data.api.ApiService
import com.example.hotelapp.data.repositoryImpl.HotelRepositoryImpl
import com.example.hotelapp.domain.repository.HotelRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindHotelRepositoryImpl(impl: HotelRepositoryImpl): HotelRepository

    companion object{
        @ApplicationScope
        @Provides
        fun provideHotelService(): ApiService = ApiFactory.apiService
    }
}