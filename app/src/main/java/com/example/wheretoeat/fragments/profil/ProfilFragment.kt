package com.example.wheretoeat.fragments.profil

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
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_profil.view.*

class ProfilFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profil, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val personId = LoginFragment.profilId
        val firstName = mUserViewModel.getFirstName(personId)
        val lastName = mUserViewModel.getLastName(personId)
        val address = mUserViewModel.getAddress(personId)
        val tel = mUserViewModel.getPhoneNumber(personId)
        val email = mUserViewModel.getEmail(personId)

        view.profilFullName.text = firstName + " " + lastName
        view.profilAddress.text = address
        view.profilEmail.text = email
        view.profilPhoneNumber.text = tel

        view.delete_btn.setOnClickListener{
            mUserViewModel.getDelete(personId)
            Toast.makeText(requireContext(), "Your account is deleted!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_profilFragment_to_listFragment)
        }

        return view
    }
}