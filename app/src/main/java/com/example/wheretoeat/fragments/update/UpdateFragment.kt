package com.example.wheretoeat.fragments.update

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.wheretoeat.R
import com.example.wheretoeat.data.UserViewModel
import com.example.wheretoeat.fragments.login.LoginFragment
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_profil.view.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.up_btn.setOnClickListener{
            updateDataInDatabase()
        }
        view.backToProfil_btn.setOnClickListener{
            findNavController().navigate(R.id.action_updateFragment_to_profilFragment)
        }
        return view
    }

    private fun updateDataInDatabase() {

        val personId = LoginFragment.profilId

        val firstName = updateFirstName.text.toString()
        val lastName = updateLastName.text.toString()
        val address = updateAddress.text.toString()
        val phoneNumber = updatePhoneNumber.text.toString()
        val email = updateEmail.text.toString()
        val password = updatePassword.text.toString()
        val passwordConfig = updatePasswordConfiguration.text.toString()

        if (!firstName.isEmpty()){
            mUserViewModel.updateFirstName(firstName, personId)
        }
        if (!lastName.isEmpty()){
            mUserViewModel.updateLastName(lastName, personId)
        }
        if (!address.isEmpty()){
            mUserViewModel.updateAddress(address, personId)
        }
        if (!phoneNumber.isEmpty()){
            mUserViewModel.updatePhoneNumber(phoneNumber, personId)
        }
        if (!email.isEmpty()){
            mUserViewModel.updateEmail(email, personId)
        }
        if (!password.isEmpty()){
            if (password == passwordConfig){
                mUserViewModel.updatePassword(password, personId)
            }
            else{
                Toast.makeText(requireContext(), "Wrong password configuration!", Toast.LENGTH_LONG).show()
                return
            }
        }
        Toast.makeText(requireContext(), "Your account is updated!", Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_updateFragment_to_profilFragment)



    }
}