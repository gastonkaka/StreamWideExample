package com.ghassen.streamwideexample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ghassen.streamwideexample.databinding.ContactLayoutRoomBinding
import com.ghassen.streamwideexample.model.Contact

class ListAdapterRoom(val context : Context, val list : List<Contact>) : RecyclerView.Adapter<ListAdapterRoom.ViewHolder>() {
    // Inner ViewHolder class
    class ViewHolder(val binding : ContactLayoutRoomBinding) : RecyclerView.ViewHolder(binding.root){}

    // function to inflate the layout for each contact
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ContactLayoutRoomBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    // function to bind the data to the ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.contactName.text = list[position].name
        holder.binding.contactNumber.text = list[position].phone

        holder.itemView.setOnClickListener{
           //we can make call here when item is clicked for example
        }
    }

    // function return the number of items in the list
    override fun getItemCount(): Int {
        return list.size
    }
}
