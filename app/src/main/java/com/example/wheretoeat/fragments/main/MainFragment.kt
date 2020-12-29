package com.example.wheretoeat.fragments.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wheretoeat.Item
import com.example.wheretoeat.ItemAdapter
import com.example.wheretoeat.MainActivity
import com.example.wheretoeat.R
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        view.profil_btn.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_profilFragment)
        }
        //view.recycler_view.adapter = ItemAdapter()
        val exampleList = generateDummyList(500)

        view.recycler_view.adapter = ItemAdapter(exampleList)
        //view.recycler_view.layoutManager = LinearLayoutManager(this)
        view.recycler_view.setHasFixedSize(true)

        return view
    }

    private fun generateDummyList(size: Int): List<Item> {
        val list = ArrayList<Item>()

        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_home
                1 -> R.drawable.ic_home
                else -> R.drawable.ic_home
            }
            val item = Item(drawable, "Item $i", "Line 2")
            list += item
        }
        return list
    }
}