package com.example.wheretoeat

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wheretoeat.fragments.main.MainFragment
import com.example.wheretoeat.fragments.profil.ProfilFragment
import com.example.wheretoeat.modul.Restaurant
import kotlinx.android.synthetic.main.one_item.view.*
import java.nio.file.Files.size

class ProfilItemAdapter(
        private val List: MutableList<Restaurant>,
        private val listener: ProfilFragment
):
        RecyclerView.Adapter<ProfilItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.one_item,
                parent, false)

        return ItemViewHolder(itemView)
    }

    //recycler view adatainak a beallitasa
    override fun onBindViewHolder(holder: ProfilItemAdapter.ItemViewHolder, position: Int) {
        val currentItem = List[position]

        Glide.with(holder.restaurantImage.context)
                .load(currentItem.image_url)
                .into(holder.restaurantImage)
        holder.restaurantName.text = currentItem.name
        holder.restaurantAddress.text = currentItem.address

    }

    override fun getItemCount(): Int {
        return List.size
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener{

        val restaurantImage: ImageView = itemView.recycler_image
        val restaurantName: TextView = itemView.recycler_name
        val restaurantAddress: TextView = itemView.recycler_address

       init {
           itemView.setOnClickListener(this)
       }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }

        }


    }
}

