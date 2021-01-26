package com.example.wheretoeat.favourites

class FavouriteRepository(private val favouriteDao: FavouriteDao) {

    suspend fun addFavourite(favourite: Favourite){
        favouriteDao.addFavourite(favourite)
    }

    fun getFavouriteNames(name: String) : List<String>{
        return favouriteDao.getFavouriteNames(name)
    }

    fun deleteFromFavourites(rname: String, name: String){
        return favouriteDao.deleteFromFavourites(rname, name)
    }

}