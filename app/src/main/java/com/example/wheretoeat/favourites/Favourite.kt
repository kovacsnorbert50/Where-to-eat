package com.example.wheretoeat.favourites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_restaurants")
data class Favourite(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val restaurantName: String,
        val userName: String

)