package com.ghassen.streamwideexample.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ghassen.streamwideexample.ContactRepository
import com.ghassen.streamwideexample.db.ContactDatabase
import com.ghassen.streamwideexample.model.Contact

class ContactViewModel(application: Application) : AndroidViewModel(application) {
    val repository : ContactRepository
    init {
        val dao = ContactDatabase.getDatabaseInstance(application).contactsDao()
        repository = ContactRepository(dao)
    }
    fun addContacts(contact : Contact){
        repository.insertContact(contact)
    }
    fun getAllContacts() : LiveData<List<Contact>> = repository.getAllContacts()
}
