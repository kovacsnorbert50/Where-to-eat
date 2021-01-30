package com.example.wheretoeat.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
    private var mIsShowPass1 = false
    private var mIsShowPass2 = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        //gombok
        view.add_btn.setOnClickListener{
            insertDataToDatabase()
        }

        view.password_image1.setOnClickListener{
            mIsShowPass1 = !mIsShowPass1
            showPassword1(mIsShowPass1)
        }

        view.password_image2.setOnClickListener{
            mIsShowPass2 = !mIsShowPass2
            showPassword2(mIsShowPass2)
        }

        view.textViewOops.setOnClickListener {
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
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

        //ha nem megfeleloek az adatok
        if (!isValidEmail(email)){
            Toast.makeText(requireContext(), "Wrong email address!", Toast.LENGTH_LONG).show()
            return
        }
        if (!isValidPassword(password)){
            Toast.makeText(requireContext(), "Min 8 : 1 uppercase, 1 lowercase, 1 number", Toast.LENGTH_LONG).show()
            return
        }

        if (inputCheck(firstName, lastName, address, phoneNumber, email, password, passwordConfig) && password == passwordConfig){
            //felhasznalo objektum letrehozasa
            val user = User(0, firstName, lastName, address, phoneNumber, email, password)
            //objektum hozzaadasa az adatbazishoz
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfull registration!", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        else{
            Toast.makeText(requireContext(), "Please fill out all the fields or check again the password.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, address: String, phoneNumber: String, email: String, password: String, passwordConfig: String): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(address) && TextUtils.isEmpty(phoneNumber) && TextUtils.isEmpty(email) && TextUtils.isEmpty(password) && TextUtils.isEmpty(passwordConfig))
    }

    private fun isValidEmail(email: String): Boolean{
        val regex = ("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$").toRegex()
        return regex.matches(email)
    }

    private fun isValidPassword(password: String): Boolean{
        val regex = ("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}\$").toRegex()
        return regex.matches(password)
    }

    private fun showPassword1(isShow: Boolean) {
        if (isShow) {
            addPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            password_image1.setImageResource(R.drawable.ic_visibility_off)
        } else {
            addPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            password_image1.setImageResource(R.drawable.ic_visibility)
        }
        addPassword.setSelection(addPassword.text.toString().length)
    }

    private fun showPassword2(isShow: Boolean) {
        if (isShow) {
            addPasswordConfig.transformationMethod = HideReturnsTransformationMethod.getInstance()
            password_image2.setImageResource(R.drawable.ic_visibility_off)
        } else {
            addPasswordConfig.transformationMethod = PasswordTransformationMethod.getInstance()
            password_image2.setImageResource(R.drawable.ic_visibility)
        }
        addPasswordConfig.setSelection(addPasswordConfig.text.toString().length)
    }
}