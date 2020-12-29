package com.example.wheretoeat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wheretoeat.modul.Restaurant
import kotlinx.android.synthetic.main.one_item.view.*

class ItemAdapter(private val List: List<Restaurant>) :
        RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.one_item,
                parent, false)

        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val currentItem = List[position]

        //holder.restaurantImage.setImageResource(currentItem.)
        holder.restaurantName.text = currentItem.name
        holder.restaurantAddress.text = currentItem.address
    }

    override fun getItemCount() = List.size

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val restaurantImage: ImageView = itemView.recycler_image
        val restaurantName: TextView = itemView.recycler_name
        val restaurantAddress: TextView = itemView.recycler_address
    }
}