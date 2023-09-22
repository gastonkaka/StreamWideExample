package com.ghassen.streamwideexample

import androidx.lifecycle.LiveData
import com.ghassen.streamwideexample.model.Contact
import com.ghassen.streamwideexample.model.ContactDao

class ContactRepository(val dao : ContactDao)
{
    // function to get all contacts from the database
    fun getAllContacts() : LiveData<List<Contact>> {
        return dao.getAllContacts()
    }

    // function to insert a contact in the database
    fun insertContact(contact : Contact) {dao.insertContact(contact)}

}
