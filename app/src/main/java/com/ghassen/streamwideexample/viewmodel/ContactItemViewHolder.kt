package com.ghassen.streamwideexample.viewmodel

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ghassen.streamwideexample.DetailContact
import com.ghassen.streamwideexample.R
import com.ghassen.streamwideexample.model.Contact
import com.ghassen.streamwideexample.utils.Constants
import com.google.gson.Gson

class ContactItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(item: Contact) = with(itemView) {
        val tvContactName = findViewById<TextView>(R.id.tvContactName)
        val tvContactPhone = findViewById<TextView>(R.id.tvContactPhone)
        val tvImageAlphabet = findViewById<TextView>(R.id.ivTextAlphabet)


        tvContactName.text = item.name
        tvContactPhone.text = item.phone
     //set the first charachter of the name as TexT view inside cardView
        tvImageAlphabet.text = item.name[0].toString()

        itemView.setOnClickListener {
            openEditContactActivity(item)
        }


    }

    private fun openEditContactActivity(item: Contact) {
        val intent = Intent(itemView.context, DetailContact::class.java)
        val itemJson = Gson().toJson(item)
        intent.putExtra(Constants.CONTACT_INTENT, itemJson)
        itemView.context.startActivity(intent)
    }
}