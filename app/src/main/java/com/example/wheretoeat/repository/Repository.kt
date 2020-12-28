package com.example.wheretoeat.repository

import com.example.wheretoeat.api.RetrofitInstance
import com.example.wheretoeat.modul.Cities
import com.example.wheretoeat.modul.Countries
import com.example.wheretoeat.modul.Restaurants

class Repository {
    suspend fun getCities(): Cities{
        return RetrofitInstance.api.getCities()
    }

    suspend fun getCountries(): Countries{
        return RetrofitInstance.api.getCountries()
    }

    suspend fun getRestaurants(): Restaurants{
        return RetrofitInstance.api.getRestaurants()
    }
}