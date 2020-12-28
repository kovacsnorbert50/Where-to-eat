package com.example.wheretoeat.data

import android.graphics.Bitmap
import android.graphics.Picture
import android.media.Image
import androidx.lifecycle.LiveData
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.wheretoeat.modul.Restaurant

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val lastName: String,
    val address: String,
    val phoneNumber: String,
    val email: String,
    val password: String

)