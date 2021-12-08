package com.example.fdbsilverfilm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fdbsilverfilm.R
import com.example.fdbsilverfilm.adapter.FilmAdapter
import com.example.fdbsilverfilm.viewmodel.FilmListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FilmListActivity : AppCompatActivity() {

    private var filmAdapter: FilmAdapter? = null
    private val model = FilmListViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_list)

        val addButton = findViewById<FloatingActionButton>(R.id.filmListAddButton)
        addButton.setOnClickListener {
            val intent = Intent(this,AddFilmActivity::class.java)
            startActivity(intent)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.filmListRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        filmAdapter = FilmAdapter(this, emptyList())
        recyclerView.adapter = filmAdapter

        model.getFilms().observe(this, { films ->
            filmAdapter?.updateValue(films)
        })

        model.loadFilms()

    }
}