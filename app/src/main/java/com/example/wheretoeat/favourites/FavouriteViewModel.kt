package com.example.wheretoeat.favourites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteViewModel(application: Application): AndroidViewModel(application) {
    private val repository: FavouriteRepository

    init {
        val favouriteDao = FavouriteDatabase.getDatabase(application).favouriteDao()
        repository = FavouriteRepository(favouriteDao)
    }

    fun addFavourite(favourite: Favourite){
        viewModelScope.launch(Dispatchers.IO){
            repository.addFavourite(favourite)
        }
    }


    fun getFavouriteNames(name: String) : List<String>{
        return repository.getFavouriteNames(name)
    }

    fun deleteFromFavourites(rname:String, name: String){
        return repository.deleteFromFavourites(rname, name)
    }
}
