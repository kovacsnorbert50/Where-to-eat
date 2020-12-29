package com.example.wheretoeat.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Query("SELECT password FROM user_table WHERE email = :e_mail")
    fun checkPassword(e_mail: String): String

    @Query("SELECT id FROM user_table WHERE email = :e_mail")
    fun getProfilId(e_mail: String): Int

    @Query("SELECT firstName FROM user_table WHERE id = :id")
    fun getFirstName(id: Int): String

    @Query("SELECT lastName FROM user_table WHERE id = :id")
    fun getLastName(id: Int): String

    @Query("SELECT address FROM user_table WHERE id = :id")
    fun getAddress(id: Int): String

    @Query("SELECT phoneNumber FROM user_table WHERE id = :id")
    fun getPhoneNumber(id: Int): String

    @Query("SELECT email FROM user_table WHERE id = :id")
    fun getEmail(id: Int): String

    @Query("SELECT password FROM user_table WHERE id = :id")
    fun getPassword(id: Int): String

    @Query("DELETE FROM user_table WHERE id = :id")
    fun getDelete(id: Int)

    @Query("UPDATE user_table SET firstName = :name WHERE id = :id ")
    fun updateFirstName(name: String, id: Int)

    @Query("UPDATE user_table SET lastName = :name WHERE id = :id ")
    fun updateLastName(name: String, id: Int)

    @Query("UPDATE user_table SET address = :addr WHERE id = :id ")
    fun updateAddress(addr: String, id: Int)

    @Query("UPDATE user_table SET phoneNumber = :tel WHERE id = :id ")
    fun updatePhoneNumber(tel: String, id: Int)

    @Query("UPDATE user_table SET email = :mail WHERE id = :id ")
    fun updateEmail(mail: String, id: Int)

    @Query("UPDATE user_table SET password = :pass WHERE id = :id ")
    fun updatePassword(pass: String, id: Int)

}