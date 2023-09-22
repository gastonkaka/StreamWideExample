package com.ghassen.streamwideexample

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ghassen.streamwideexample.adapter.ListAdapter
import com.ghassen.streamwideexample.model.Contact
import com.ghassen.streamwideexample.viewmodel.ContactViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private lateinit var rcContacts: RecyclerView
    lateinit var contactsList: MutableList<Contact>
    lateinit var listadapter: ListAdapter
    private lateinit var btnviewdatainroom: FloatingActionButton
    val viewModel : ContactViewModel by viewModels()



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "mes contacts"
        rcContacts = findViewById(R.id.rcContacts)
        val layoutManager = LinearLayoutManager(this)
        rcContacts.layoutManager =  layoutManager


    //get list of reél contacts and assign them to our contactlist
        contactsList = ArrayList()
        contactsList = getphonecontact()
        println(contactsList.size)

    //insert data in our database local
        for (i in 0 until contactsList.size) {
            inserttosqlite(contactsList.get(i))
        }

    // initializing our adapter
        listadapter = ListAdapter(contactsList)

    //setting adapter to our recycler view.
        rcContacts.adapter = listadapter
        listadapter.notifyDataSetChanged()

    // initializing our Button to navigate to 'contacts stored page'
        btnviewdatainroom = findViewById(R.id.btnviewdataroom)
        btnviewdatainroom.setOnClickListener {
            startActivity(Intent(this, MainActivityFromRoom::class.java))
        }
    }



    //function to get List of contact as mutablelist of object
    @SuppressLint("Range")
    fun getphonecontact(): MutableList<Contact> {

        val names = mutableListOf<Contact>()
        val cr = contentResolver
        val cur = cr.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
            null, null, null)
        if (cur!!.count > 0) {
            while (cur.moveToNext()) {
                val id = cur.getInt(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID))
                val name = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val phone = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

                names.add(Contact(id,name,phone))
            }
        }
        //println(names.size)
        return names
    }
    //function to save data of contact in roomdatabase
    private fun inserttosqlite(contact: Contact) {
        val idc = contact.id
        val name= contact.name
        val number = contact.phone
     // create new contact object
        val data = Contact(idc,name = name , phone = number)
    // call addContacts function from the ViewModel class
        viewModel.addContacts(data)
        // display a Toast to confirm the save
        Toast.makeText(this, "Saved in local", Toast.LENGTH_SHORT).show()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //get our inflater
        val inflater = menuInflater

        //inflating our menu file.
        inflater.inflate(R.menu.search_menu, menu)

        //get our menu item.
        val searchItem: MenuItem = menu.findItem(R.id.actionSearch)

        // get search view of our item.
        val searchView: androidx.appcompat.widget.SearchView = searchItem.actionView as androidx.appcompat.widget.SearchView


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(msg: String): Boolean {
                // calling a method to filter our recycler view.
                filter(msg)
                return false
            }
        })
        return true
    }

//function to filter our data
    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val filteredlist: ArrayList<Contact> = ArrayList()

        //compare elements.
        for (item in contactsList) {
            // checking if the entered string matched with any contact from recycle view.
            if ((item.name.toLowerCase().contains(text.toLowerCase()))||(item.phone.contains(text.toLowerCase()))){
                // add it to our filtered list.
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            // list to our adapter class.
            listadapter.filterList(filteredlist)
        }
    }
}

