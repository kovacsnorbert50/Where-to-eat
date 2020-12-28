package com.example.wheretoeat.fragments.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.room.Query
import com.example.wheretoeat.R
import com.example.wheretoeat.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_login, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.login_btn2.setOnClickListener{
            loginToApplication()
        }

        return view
    }

    companion object{
        var profilId: Int = -1
    }

    private fun loginToApplication() {
        val email = addLoginEmail.text.toString()
        val passw = addLoginPassword.text.toString()

        val passwordInDatabase = mUserViewModel.checkPassword(email)

        if(email.isEmpty()){
            Toast.makeText(requireContext(), "Forgot to write your email address!", Toast.LENGTH_LONG).show()
            return
        }

        if (passw.isEmpty()){
            Toast.makeText(requireContext(), "Forgot to write your password!", Toast.LENGTH_LONG).show()
            return
        }

        if (passwordInDatabase == passw){
            Toast.makeText(requireContext(), "You are logged in successfully!", Toast.LENGTH_LONG).show()
            profilId = mUserViewModel.getProfilId(email)
            //Log.d("Id = ", profilId.toString())
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }
        else{
            Toast.makeText(requireContext(), "Wrong email or password!", Toast.LENGTH_LONG).show()
        }
    }

}