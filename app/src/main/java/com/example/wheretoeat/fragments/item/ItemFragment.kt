package com.example.wheretoeat.fragments.item

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.wheretoeat.MainViewModel
import com.example.wheretoeat.R
import com.example.wheretoeat.fragments.main.MainFragment
import kotlinx.android.synthetic.main.fragment_item.view.*
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.one_item.view.*

class ItemFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_item, container, false)

        val position = MainFragment.itemPosition
        val restList = MainFragment.restaurantsList

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

        return view
    }

    companion object{
        var longitude: Double = 0.0
        var latitude: Double = 0.0
        var area: String = ""
    }
}