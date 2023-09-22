package com.ghassen.streamwideexample.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ghassen.streamwideexample.model.Contact
import com.ghassen.streamwideexample.model.ContactDao

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactDatabase : RoomDatabase() {
    // Dao interface for the database
    abstract fun contactsDao() : ContactDao
    companion object {
        @Volatile
        var INSTANCE : ContactDatabase?=null
    // Singleton instance of the database
        fun getDatabaseInstance(context : Context) : ContactDatabase{
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }

     // one instance of the database is created
            synchronized(this){
                val roomDatabaseInstance = Room.databaseBuilder(context,ContactDatabase::class.java,"Contact").allowMainThreadQueries().build()
                INSTANCE = roomDatabaseInstance
                return roomDatabaseInstance
            }
        }

    }
}
