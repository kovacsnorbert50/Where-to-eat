package com.example.wheretoeat.favourites

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavouriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavourite(favourite: Favourite)

    @Query("SELECT restaurantName FROM favourite_restaurants WHERE userName = :name")
    fun getFavouriteNames(name: String): List<String>

    @Query("DELETE FROM favourite_restaurants WHERE restaurantName = :rname and userName = :name")
    fun deleteFromFavourites(rname: String, name: String)
}