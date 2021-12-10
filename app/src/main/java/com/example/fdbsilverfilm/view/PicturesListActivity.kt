package com.example.fdbsilverfilm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fdbsilverfilm.R
import com.example.fdbsilverfilm.adapter.PicturesListAdapter
import com.example.fdbsilverfilm.model.Film
import com.example.fdbsilverfilm.model.Globals
import com.example.fdbsilverfilm.viewmodel.PicturesListViewModel

class PicturesListActivity : AppCompatActivity() {
    var vm: PicturesListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pictures_list)

        vm = PicturesListViewModel(intent.getSerializableExtra(Globals.FILM_EXTRA_TAG) as Film)

        val recyclerView: RecyclerView = findViewById(R.id.pictures_list_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PicturesListAdapter(vm?.data, this)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }
}