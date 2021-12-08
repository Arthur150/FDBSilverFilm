package com.example.fdbsilverfilm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fdbsilverfilm.R
import com.example.fdbsilverfilm.model.Film
import com.example.fdbsilverfilm.model.Globals
import com.example.fdbsilverfilm.viewmodel.PicturesListViewModel

class PicturesListActivity : AppCompatActivity() {
    var vm : PicturesListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pictures_list)

        vm = PicturesListViewModel(intent.getSerializableExtra(Globals.FILM_EXTRA_TAG) as Film)

        
    }
}