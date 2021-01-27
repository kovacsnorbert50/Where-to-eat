package com.example.wheretoeat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wheretoeat.modul.Cities
import com.example.wheretoeat.modul.Countries
import com.example.wheretoeat.modul.Restaurant
import com.example.wheretoeat.modul.Restaurants
import com.example.wheretoeat.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse1: MutableLiveData<Cities> = MutableLiveData()
    val myResponse2: MutableLiveData<Countries> = MutableLiveData()
    val myResponse3: MutableLiveData<Restaurants> = MutableLiveData()
    //vendeglok elhelyezese egy listaban (MainFragment)
    val restaurant: MutableList<Restaurant> = mutableListOf()
    //vendeglok elhelyezese egy listaban (ProfilFragment)
    val favouriteRestaurant: MutableList<Restaurant> = mutableListOf()

    fun getCities(){
        viewModelScope.launch {
            val response1 = repository.getCities()
            myResponse1.value = response1
        }
    }

    fun getCountries(){
        viewModelScope.launch {
            val response = repository.getCountries()
            myResponse2.value = response
        }
    }

    fun getRestaurants(){
        viewModelScope.launch {
            val response3 = repository.getRestaurants()
            myResponse3.value = response3
        }
    }



}