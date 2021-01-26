package com.example.wheretoeat.fragments.item

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.wheretoeat.MainViewModel
import com.example.wheretoeat.R
import com.example.wheretoeat.data.UserViewModel
import com.example.wheretoeat.favourites.Favourite
import com.example.wheretoeat.favourites.FavouriteViewModel
import com.example.wheretoeat.fragments.login.LoginFragment
import com.example.wheretoeat.fragments.main.MainFragment
import kotlinx.android.synthetic.main.fragment_item.*
import kotlinx.android.synthetic.main.fragment_item.view.*
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.one_item.view.*
import kotlin.math.log

class ItemFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var mFavouriteViewModel: FavouriteViewModel
    val personId = LoginFragment.profilId

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_item, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mFavouriteViewModel = ViewModelProvider(this).get(FavouriteViewModel::class.java)

        val position = MainFragment.itemPosition
        val restList = MainFragment.restaurantsList
        val userName = mUserViewModel.getFirstName(personId) + " " + mUserViewModel.getLastName(personId)
        val favouriteList: List<String> = mFavouriteViewModel.getFavouriteNames(userName)

        view.itemName.text = restList[position].name
        view.itemAddress.text = "Address: " + restList[position].address
        view.itemCity.text = "City: " + restList[position].city
        view.itemPhoneNumber.text = "Tel: " + restList[position].phone
        view.itemPostalCode.text = "Postal code: " + restList[position].postal_code

        longitude = restList[position].lng
        latitude = restList[position].lat
        area = restList[position].area

        Glide.with(view.context)
                .load(restList[position].image_url)
                .into(view.itemImage)

        view.map_btn.setOnClickListener{
            findNavController().navigate(R.id.action_itemFragment_to_mapsFragment)
        }

        view.backToHomeFromItem_btn.setOnClickListener{
            findNavController().navigate(R.id.action_itemFragment_to_mainFragment)
        }
        var flag: Int = 0
        for (i in favouriteList){
            if (i == restList[position].name){
                flag = 1
            }
        }

        if (flag == 1){
            view.addToFavourites_btn.visibility = View.GONE
            view.deleteFromFavourites.visibility = View.VISIBLE
        }
        else{
            view.deleteFromFavourites.visibility = View.GONE
            view.addToFavourites_btn.visibility = View.VISIBLE
        }

        view.addToFavourites_btn.setOnClickListener{
            val favourite = Favourite(0, restList[position].name, userName)

            mFavouriteViewModel.addFavourite(favourite)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()

            view.addToFavourites_btn.visibility = View.GONE
            view.deleteFromFavourites.visibility = View.VISIBLE
        }

        view.deleteFromFavourites.setOnClickListener{
            mFavouriteViewModel.deleteFromFavourites(restList[position].name, userName)
            Toast.makeText(requireContext(), "Successfully deleted!", Toast.LENGTH_LONG).show()

            view.deleteFromFavourites.visibility = View.GONE
            view.addToFavourites_btn.visibility = View.VISIBLE
        }

        return view
    }

    companion object{
        var longitude: Double = 0.0
        var latitude: Double = 0.0
        var area: String = ""
    }
}