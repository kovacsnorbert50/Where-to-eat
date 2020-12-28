package com.example.wheretoeat.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.wheretoeat.R
import com.example.wheretoeat.data.User
import com.example.wheretoeat.data.UserViewModel
import com.example.wheretoeat.modul.Restaurant
import com.example.wheretoeat.modul.Restaurants
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.add_btn.setOnClickListener{
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val firstName = addFirstName_et.text.toString()
        val lastName = addLastName_et.text.toString()
        val address = addAddress.text.toString()
        val phoneNumber = addPhoneNumber.text.toString()
        val email = addEmail.text.toString()
        val password = addPassword.text.toString()
        val passwordConfig = addPasswordConfig.text.toString()

        if (inputCheck(firstName, lastName, address, phoneNumber, email, password, passwordConfig) && password == passwordConfig){
            //Create user object
            val user = User(0, firstName, lastName, address, phoneNumber, email, password)
            //Add data to database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            //Navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        else{
            Toast.makeText(requireContext(), "Please fill out all fields or wrong password configuration.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, address: String, phoneNumber: String, email: String, password: String, passwordConfig: String): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(address) && TextUtils.isEmpty(phoneNumber) && TextUtils.isEmpty(email) && TextUtils.isEmpty(password) && TextUtils.isEmpty(passwordConfig))
    }
}