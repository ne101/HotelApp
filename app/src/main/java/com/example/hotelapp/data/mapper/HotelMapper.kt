package com.example.hotelapp.data.mapper

import com.example.hotelapp.data.model.booking.BookingResponse
import com.example.hotelapp.data.model.hotel.AboutTheHotel
import com.example.hotelapp.data.model.hotel.HotelResponse
import com.example.hotelapp.data.model.room.Room
import com.example.hotelapp.data.model.room.RoomResponse
import com.example.hotelapp.domain.entity.booking.BookingEntity
import com.example.hotelapp.domain.entity.hotel.AboutTheHotelEntity
import com.example.hotelapp.domain.entity.hotel.HotelEntity
import com.example.hotelapp.domain.entity.room.RoomEntity
import com.example.hotelapp.domain.entity.room.RoomListEntity
import javax.inject.Inject

class HotelMapper @Inject constructor() {
    fun mapHotelResponseToHotelEntity(hotelResponse: HotelResponse): HotelEntity {
        return HotelEntity(
            about_the_hotel = mapAboutTheHotelToAboutTheHotelEntity(hotelResponse.about_the_hotel),
            adress = hotelResponse.adress,
            id = hotelResponse.id,
            image_urls = hotelResponse.image_urls,
            minimal_price = hotelResponse.minimal_price,
            name = hotelResponse.name,
            price_for_it = hotelResponse.price_for_it,
            rating = hotelResponse.rating,
            rating_name = hotelResponse.rating_name
        )
    }

    private fun mapAboutTheHotelToAboutTheHotelEntity(aboutTheHotel: AboutTheHotel): AboutTheHotelEntity {
        return AboutTheHotelEntity(
            description = aboutTheHotel.description,
            peculiarities = aboutTheHotel.peculiarities
        )
    }

    private fun mapRoomToRoomEntity(room: Room): RoomEntity {
        return RoomEntity(
            id = room.id,
            image_urls = room.image_urls,
            name = room.name,
            peculiarities = room.peculiarities,
            price = room.price,
            price_per = room.price_per
        )
    }

    fun mapRoomResponseToRoomListEntity(roomResponse: RoomResponse): RoomListEntity {
        return RoomListEntity(
            rooms = roomResponse.rooms.map { mapRoomToRoomEntity(it) }
        )
    }

    fun mapBookingResponseToBookingEntity(bookingResponse: BookingResponse): BookingEntity {
        return BookingEntity(
            arrival_country = bookingResponse.arrival_country,
            departure = bookingResponse.departure,
            fuel_charge = bookingResponse.fuel_charge,
            horating = bookingResponse.horating,
            hotel_adress = bookingResponse.hotel_adress,
            hotel_name = bookingResponse.hotel_name,
            id = bookingResponse.id,
            number_of_nights = bookingResponse.number_of_nights,
            nutrition = bookingResponse.nutrition,
            rating_name = bookingResponse.rating_name,
            room = bookingResponse.room,
            service_charge = bookingResponse.service_charge,
            tour_price = bookingResponse.tour_price,
            tour_date_start = bookingResponse.tour_date_start,
            tour_date_stop = bookingResponse.tour_date_stop
            )
    }
}