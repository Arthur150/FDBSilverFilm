package com.example.fdbsilverfilm.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fdbsilverfilm.R
import com.example.fdbsilverfilm.adapter.FilmAdapter
import com.example.fdbsilverfilm.model.Globals
import com.example.fdbsilverfilm.viewmodel.FilmListViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FilmListActivity : AppCompatActivity() {

    private var filmAdapter: FilmAdapter? = null
    private val model = FilmListViewModel(this)

    private var checkedChip : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_list)


        model.loadFilms()

        val addButton = findViewById<FloatingActionButton>(R.id.filmListAddButton)
        addButton.setOnClickListener {
            val intent = Intent(this, AddFilmActivity::class.java)
            startActivity(intent)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.filmListRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        filmAdapter = FilmAdapter(this, emptyList(), model)
        recyclerView.adapter = filmAdapter

        model.getFilms().observe(this, { films ->
            filmAdapter?.updateValue(films)
        })

        val chipShowNotFull = findViewById<Chip>(R.id.show_not_full_films_chip)
        val chipShowAll = findViewById<Chip>(R.id.show_all_films_chip)
        val chipShowFull = findViewById<Chip>(R.id.show_full_films_chip)

        checkedChip = chipShowNotFull.id
        chipShowNotFull.setChipBackgroundColorResource(R.color.orange)

        chipShowNotFull.setOnClickListener {
            if (checkedChip != chipShowNotFull.id){
                chipShowAll.setChipBackgroundColorResource(R.color.secondary_variant_grey)
                chipShowFull.setChipBackgroundColorResource(R.color.secondary_variant_grey)
                chipShowNotFull.setChipBackgroundColorResource(R.color.orange)
                chipShowNotFull.isCloseIconVisible = false
                recyclerView.adapter = FilmAdapter(this, model.getNotFullFilms() , model)
                checkedChip = chipShowNotFull.id
            }
        }

        chipShowAll.setOnClickListener {
            if ( chipShowAll.id != checkedChip) {
                chipShowFull.setChipBackgroundColorResource(R.color.secondary_variant_grey)
                chipShowNotFull.setChipBackgroundColorResource(R.color.secondary_variant_grey)
                chipShowAll.setChipBackgroundColorResource(R.color.orange)
                chipShowAll.isCloseIconVisible = false
                recyclerView.adapter = FilmAdapter(this, model.getAllFilms(), model)
                checkedChip = chipShowAll.id
            }
        }

        chipShowFull.setOnClickListener {
            if ( chipShowFull.id != checkedChip) {
                chipShowAll.setChipBackgroundColorResource(R.color.secondary_variant_grey)
                chipShowNotFull.setChipBackgroundColorResource(R.color.secondary_variant_grey)
                chipShowFull.setChipBackgroundColorResource(R.color.orange)
                chipShowFull.isCloseIconVisible = false
                recyclerView.adapter = FilmAdapter(this, model.getFullFilms(), model)
                checkedChip = chipShowFull.id
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }

}