package com.example.fdbsilverfilm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fdbsilverfilm.R
import com.example.fdbsilverfilm.adapter.FilmAdapter
import com.example.fdbsilverfilm.viewmodel.FilmListViewModel

class FilmListActivity : AppCompatActivity() {

    private var filmAdapter: FilmAdapter? = null
    private val model = FilmListViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_list)

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