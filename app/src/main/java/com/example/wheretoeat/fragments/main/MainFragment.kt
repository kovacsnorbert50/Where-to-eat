package com.example.wheretoeat.fragments.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wheretoeat.*
import com.example.wheretoeat.modul.Restaurant
import com.example.wheretoeat.repository.Repository
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyc_view: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        view.profil_btn.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_profilFragment)
        }

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getRestaurants()

        viewModel.myResponse3.observe(viewLifecycleOwner, Observer { response ->
            recyc_view.adapter = ItemAdapter(response.restaurants, this)
            viewModel.restaurant.addAll(response.restaurants)
        })

        restaurantsList = viewModel.restaurant

        recyc_view = view.recycler_view
        view.recycler_view.layoutManager = LinearLayoutManager(this.context)
        view.recycler_view.setHasFixedSize(true)

        return view
    }

    companion object{
        var restaurantsList: List<Restaurant> = emptyList()
        var positionList: List<Int> = emptyList()
        var itemPosition: Int = -1
    }

    fun onItemClick(position: Int) {
        itemPosition = position
        findNavController().navigate(R.id.action_mainFragment_to_itemFragment)
    }

}