package com.example.wheretoeat.fragments.update

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
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
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_profil.view.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    private var mIsShowPass1 = false
    private var mIsShowPass2 = false

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
        view.update_password_image1.setOnClickListener{
            mIsShowPass1 = !mIsShowPass1
            showPassword1(mIsShowPass1)
        }

        view.update_password_image2.setOnClickListener{
            mIsShowPass2 = !mIsShowPass2
            showPassword2(mIsShowPass2)
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

    private fun showPassword1(isShow: Boolean) {
        if (isShow) {
            updatePassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            update_password_image1.setImageResource(R.drawable.ic_visibility_off)
        } else {
            updatePassword.transformationMethod = PasswordTransformationMethod.getInstance()
            update_password_image1.setImageResource(R.drawable.ic_visibility)
        }
        updatePassword.setSelection(updatePassword.text.toString().length)
    }

    private fun showPassword2(isShow: Boolean) {
        if (isShow) {
            updatePasswordConfiguration.transformationMethod = HideReturnsTransformationMethod.getInstance()
            update_password_image2.setImageResource(R.drawable.ic_visibility_off)
        } else {
            updatePasswordConfiguration.transformationMethod = PasswordTransformationMethod.getInstance()
            update_password_image2.setImageResource(R.drawable.ic_visibility)
        }
        updatePasswordConfiguration.setSelection(updatePasswordConfiguration.text.toString().length)
    }
}