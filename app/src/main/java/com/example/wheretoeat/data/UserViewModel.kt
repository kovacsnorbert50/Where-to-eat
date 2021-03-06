package com.example.wheretoeat.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    //val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        //readAllData = repository.readAllData
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun checkPassword(e_mail: String): String{
        return repository.checkPassword(e_mail)
    }

    fun getProfilId(e_mail: String): Int{
        return repository.getProfilId(e_mail)
    }

    fun getFirstName(id: Int): String{
        return repository.getFirstName(id)
    }

    fun getLastName(id: Int): String{
        return repository.getLastName(id)
    }

    fun getAddress(id: Int): String{
        return repository.getAddress(id)
    }

    fun getPhoneNumber(id: Int): String{
        return repository.getPhoneNumber(id)
    }

    fun getEmail(id: Int): String{
        return repository.getEmail(id)
    }

    fun getPassword(id: Int): String{
        return repository.getPassword(id)
    }

    fun getDelete(id: Int){
        repository.getDelete(id)
    }

    fun updateFirstName(name: String, id: Int){
        repository.updateFirstName(name, id)
    }

    fun updateLastName(name: String, id: Int){
        repository.updateLastName(name, id)
    }

    fun updateAddress(addr: String, id: Int){
        repository.updateAddress(addr, id)
    }

    fun updatePhoneNumber(tel: String, id: Int){
        repository.updatePhoneNumber(tel, id)
    }

    fun updateEmail(mail: String, id: Int){
        repository.updateEmail(mail, id)
    }

    fun updatePassword(pass: String, id: Int){
        repository.updatePassword(pass, id)
    }
}