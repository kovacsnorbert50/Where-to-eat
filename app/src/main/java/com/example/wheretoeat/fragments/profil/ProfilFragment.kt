package com.example.wheretoeat.fragments.profil

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wheretoeat.*
import com.example.wheretoeat.data.UserViewModel
import com.example.wheretoeat.favourites.FavouriteViewModel
import com.example.wheretoeat.fragments.login.LoginFragment
import com.example.wheretoeat.fragments.main.MainFragment
import com.example.wheretoeat.modul.Restaurant
import com.example.wheretoeat.repository.Repository
import kotlinx.android.synthetic.main.fragment_profil.view.*

class ProfilFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var mFavouriteViewModel: FavouriteViewModel
    private lateinit var viewModel: MainViewModel
    private lateinit var recyc_view: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profil, container, false)

        view.backToHome_btn.setOnClickListener{
            findNavController().navigate(R.id.action_profilFragment_to_mainFragment)
        }

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mFavouriteViewModel = ViewModelProvider(this).get(FavouriteViewModel::class.java)

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

        //- - - - -
        val tempList: MutableList<Restaurant> = mutableListOf()
        var favouriteList: List<String> = listOf()

        MainFragment.flag = 0

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getRestaurants()

        val name = mUserViewModel.getFirstName(personId) + " " + mUserViewModel.getLastName(personId)
        favouriteList = mFavouriteViewModel.getFavouriteNames(name)

        //kedvenc vendeglok elhelyezese a recycler viewban
        viewModel.myResponse3.observe(viewLifecycleOwner, Observer { response ->

            for (i in response.restaurants){
                for(j in favouriteList){
                    if(i.name == j){
                        tempList.add(i)
                    }
                }
            }

            recyc_view.adapter = ProfilItemAdapter(tempList, this)
            viewModel.favouriteRestaurant.addAll(tempList)
        })

        //mentes
        favouriteRestaurantsList = viewModel.favouriteRestaurant

        recyc_view = view.findViewById(R.id.profilRecycler)
        recyc_view.layoutManager = LinearLayoutManager(requireContext())
        recyc_view.setHasFixedSize(true)

        //gombok
        view.delete_btn.setOnClickListener{
            mUserViewModel.getDelete(personId)
            Toast.makeText(requireContext(), "Your account is deleted!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_profilFragment_to_listFragment)
        }

        view.update_btn.setOnClickListener{
            findNavController().navigate(R.id.action_profilFragment_to_updateFragment)
        }

        view.logOut_btn.setOnClickListener{
            findNavController().navigate(R.id.action_profilFragment_to_loginFragment)
        }

        return view
    }

    companion object{
        var favouriteRestaurantsList: List<Restaurant> = emptyList()
        var itemPos: Int = -1
    }

    fun onItemClick(position: Int) {
        itemPos = position
        findNavController().navigate(R.id.action_profilFragment_to_itemFragment)
    }
}