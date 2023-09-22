package com.ghassen.streamwideexample.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contact")
data class Contact(

    var id : Int?=null,
    var name: String,
    @PrimaryKey
    var phone: String,
    var address: String? = null,
    var email: String? = null,
    var birthday: String? = null
)
