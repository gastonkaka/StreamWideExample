package com.ghassen.streamwideexample.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ContactDao  {
    @Query("Select * from Contact")
    fun getAllContacts() : LiveData<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insertContact(contact : Contact)

    @Delete
    fun delete(contact: Contact)
}