package com.example.wheretoeat.fragments.item

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
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
import com.example.wheretoeat.fragments.profil.ProfilFragment
import com.google.android.gms.tasks.Tasks.call
import kotlinx.android.synthetic.main.fragment_item.*
import kotlinx.android.synthetic.main.fragment_item.view.*
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.one_item.view.*
import android.Manifest
import kotlin.math.log

class ItemFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var mFavouriteViewModel: FavouriteViewModel
    val personId = LoginFragment.profilId
    var telNumber = ""
    val REQUEST_PHONE_CALL = 1
    var webPage = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_item, container, false)

        //inicializalas
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mFavouriteViewModel = ViewModelProvider(this).get(FavouriteViewModel::class.java)

        //flag-et hasznalunk azert, hogy tudjuk melyik fragmentrol hivjuk az itemFragmentet(mas-mas lista es pozicio miatt)
        //megfelelo vendeglo lista es pozicio beallitasa(flag = 0 ha mainFragmentrol hivjuk , profilFragment eseten 1)
        var position = MainFragment.itemPosition
        var restList = MainFragment.restaurantsList

        if (MainFragment.flag == 0){
            position = ProfilFragment.itemPos
            restList = ProfilFragment.favouriteRestaurantsList
        }

        val userName = mUserViewModel.getFirstName(personId) + " " + mUserViewModel.getLastName(personId)
        val favouriteList: List<String> = mFavouriteViewModel.getFavouriteNames(userName)

        view.itemName.text = restList[position].name
        view.itemAddress.text = "Address: " + restList[position].address
        view.itemCity.text = "City: " + restList[position].city
        view.itemPhoneNumber.text = "Tel: " + restList[position].phone.take(10)
        view.itemPostalCode.text = "Postal code: " + restList[position].postal_code

        //terkephez szukseges adatok
        longitude = restList[position].lng
        latitude = restList[position].lat
        area = restList[position].area
        telNumber = restList[position].phone.take(10) //hibas adatok miatt csak az elso 10 szamot hasznaljuk
        webPage = restList[position].reserve_url

        //kep betoltese
        Glide.with(view.context)
                .load(restList[position].image_url)
                .into(view.itemImage)

        //gombok
        view.map_btn.setOnClickListener{
            findNavController().navigate(R.id.action_itemFragment_to_mapsFragment)
        }

        view.backToHomeFromItem_btn.setOnClickListener{
            findNavController().navigate(R.id.action_itemFragment_to_mainFragment)
        }

        view.backToProfilFromItem_btn.setOnClickListener {
            findNavController().navigate(R.id.action_itemFragment_to_profilFragment)
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

            //kezdetben mindig az add gomb lathato masik eltunik
            view.deleteFromFavourites.visibility = View.GONE
            view.addToFavourites_btn.visibility = View.VISIBLE
        }

        view.itemPhoneNumber.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_CALL
            intent.data = Uri.parse("tel:$telNumber")
            startActivity(intent)
        }

        view.web_btn.setOnClickListener {
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(webPage))
            startActivity(webIntent)
        }


        //Add to favourites / Delete from favourites valtogatasa, a vendeglo allapotatol fuggoen
        var flag: Int = 0
        for (i in favouriteList){
            if (i == restList[position].name){
                flag = 1
            }
        }

        if (flag == 1){
            //hozzaad / torles a kedvenc listabol, megjelenes beallitasa
            view.addToFavourites_btn.visibility = View.GONE
            view.deleteFromFavourites.visibility = View.VISIBLE

            //visszalepo gomb megjelenes beallitasa
            view.backToProfilFromItem_btn.visibility = View.VISIBLE
            view.backToHomeFromItem_btn.visibility = View.GONE
        }
        else{
            view.deleteFromFavourites.visibility = View.GONE
            view.addToFavourites_btn.visibility = View.VISIBLE

            view.backToProfilFromItem_btn.visibility = View.GONE
            view.backToHomeFromItem_btn.visibility = View.VISIBLE
        }

        return view
    }

    companion object{
        var longitude: Double = 0.0
        var latitude: Double = 0.0
        var area: String = ""
    }
}