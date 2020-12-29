package com.example.wheretoeat.data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    fun checkPassword(e_mail: String): String{
        return userDao.checkPassword(e_mail)
    }

    fun getProfilId(e_mail: String): Int{
        return userDao.getProfilId(e_mail)
    }

    fun getFirstName(id: Int): String{
        return userDao.getFirstName(id)
    }

    fun getLastName(id: Int): String{
        return userDao.getLastName(id)
    }

    fun getAddress(id: Int): String{
        return userDao.getAddress(id)
    }

    fun getPhoneNumber(id: Int): String{
        return userDao.getPhoneNumber(id)
    }

    fun getEmail(id: Int): String{
        return userDao.getEmail(id)
    }

    fun getPassword(id: Int): String{
        return userDao.getPassword(id)
    }

    fun getDelete(id: Int){
        userDao.getDelete(id)
    }

    fun updateFirstName(name: String, id: Int){
        userDao.updateFirstName(name, id)
    }

    fun updateLastName(name: String, id: Int){
        userDao.updateLastName(name, id)
    }

    fun updateAddress(addr: String, id: Int){
        userDao.updateAddress(addr, id)
    }

    fun updatePhoneNumber(tel: String, id: Int){
        userDao.updatePhoneNumber(tel, id)
    }

    fun updateEmail(mail: String, id: Int){
        userDao.updateEmail(mail, id)
    }

    fun updatePassword(pass: String, id: Int){
        userDao.updatePassword(pass, id)
    }
}