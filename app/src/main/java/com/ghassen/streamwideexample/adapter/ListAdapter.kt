package com.ghassen.streamwideexample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ghassen.streamwideexample.R
import com.ghassen.streamwideexample.model.Contact
import com.ghassen.streamwideexample.viewmodel.ContactItemViewHolder

class ListAdapter(
    private var contacts: MutableList<Contact>
) : RecyclerView.Adapter<ContactItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ContactItemViewHolder(view)
    }
    fun filterList(filterlist: ArrayList<Contact>) {
        //  add our filtered
        // list in our contact array list.
        contacts = filterlist
        //notify our adapter as change in recycler view data.
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ContactItemViewHolder, position: Int) {
        val item = contacts[position]
        holder.bindView(item)
    }

    override fun getItemCount(): Int {
        // our size of our list
        return contacts.size
    }

}