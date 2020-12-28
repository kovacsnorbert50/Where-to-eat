package com.example.wheretoeat.api

import com.example.wheretoeat.modul.Cities
import com.example.wheretoeat.modul.Countries
import com.example.wheretoeat.modul.Restaurants
import retrofit2.http.GET


interface SimpleApi {
    @GET("cities")
    suspend fun getCities(): Cities

    @GET("countries")
    suspend fun getCountries(): Countries

    @GET("restaurants")
    suspend fun getRestaurants(): Restaurants
}