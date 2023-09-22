package com.ghassen.streamwideexample

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ghassen.streamwideexample.adapter.ListAdapterRoom
import com.ghassen.streamwideexample.databinding.ActivityMainFromRoomBinding
import com.ghassen.streamwideexample.viewmodel.ContactViewModel

class MainActivityFromRoom : AppCompatActivity() {
    // variable to inflate the layout for the activity
    private lateinit var binding: ActivityMainFromRoomBinding
    private lateinit var rcContactss: RecyclerView

    // variable to access the ViewModel class
    val viewModel : ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "mes contacts stockeés"
        // inflate the layout
        binding = ActivityMainFromRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rcContactss = findViewById(R.id.recyclerView)
        // set onClickListener for the floating action button


        // Observe the LiveData returned by the (getAllContacts) method
        viewModel.getAllContacts().observe(this , Observer {  list->

            // set the layout manager and the adapter for the recycler view
           rcContactss.layoutManager = LinearLayoutManager(applicationContext)
            rcContactss.adapter = ListAdapterRoom(this,list)
        })
    }
}