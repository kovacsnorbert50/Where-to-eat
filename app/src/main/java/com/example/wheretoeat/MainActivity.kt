package com.example.wheretoeat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wheretoeat.repository.Repository
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        setupActionBarWithNavController(findNavController(R.id.fragment))

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.getCities()
        viewModel.myResponse1.observe(this, Observer {response ->
            Log.d("Cities: ", response.cities.toString())
        })

        viewModel.getCountries()
        viewModel.myResponse2.observe(this, Observer {response ->
            Log.d("Countries: ", response.countries.toString())
        })

        viewModel.getRestaurants()
        viewModel.myResponse3.observe(this, Observer {response ->
            Log.d("Restaurants: ", response.restaurants.toString())
        })
    }
}