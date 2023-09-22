package com.ghassen.streamwideexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ghassen.streamwideexample.model.Contact
import com.ghassen.streamwideexample.utils.Constants.CONTACT_INTENT
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson

class DetailContact : AppCompatActivity() {

    private lateinit var tiName: TextInputEditText
    private lateinit var tiPhone: TextInputEditText
    private lateinit var tiAddress: TextInputEditText
    private lateinit var tiEmail: TextInputEditText
    private lateinit var tiBirthday: TextInputEditText
    private lateinit var contact: Contact

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_edit_contact)
        initPropertiesWithUIComponents()
        loadContactFromIntentJson()

    }

    private fun initPropertiesWithUIComponents() {
        title = "detail contact"
        tiName = findViewById(R.id.tiName)
        tiPhone = findViewById(R.id.tiPhone)
        tiAddress = findViewById(R.id.tiAddress)

        tiEmail = findViewById(R.id.tiEmail)
        tiBirthday = findViewById(R.id.tiBirthday)

    }

    private fun loadContactFromIntentJson() {
        val contactJson = intent.getStringExtra(CONTACT_INTENT)
        contact = Gson().fromJson(contactJson, Contact::class.java)
        contact.also {
            tiName.setText(it.name)
            tiPhone.setText(it.phone)
            tiEmail.setText(it.email)
        }
    }
}