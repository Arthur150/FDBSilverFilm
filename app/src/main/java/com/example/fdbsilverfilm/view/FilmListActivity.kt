package com.example.fdbsilverfilm.view

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fdbsilverfilm.R
import com.example.fdbsilverfilm.adapter.FilmAdapter
import com.example.fdbsilverfilm.viewmodel.FilmListViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FilmListActivity : AppCompatActivity() {

    private val model = FilmListViewModel(this)
    private var allFilmAdapter = FilmAdapter(this, emptyList(), model)
    private var notFullFilmAdapter = FilmAdapter(this, emptyList(), model)
    private var fullFilmAdapter = FilmAdapter(this, emptyList(), model)

    private var checkedChip: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_list)

        val addButton = findViewById<FloatingActionButton>(R.id.filmListAddButton)
        addButton.imageTintList = ColorStateList.valueOf(getColor(R.color.secondary_variant_grey))
        addButton.setOnClickListener {
            val intent = Intent(this, AddFilmActivity::class.java)
            startActivity(intent)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.filmListRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        allFilmAdapter = FilmAdapter(this, emptyList(), model)
        recyclerView.adapter = allFilmAdapter

        model.getFilms().observe(this, {
            allFilmAdapter.updateValue(model.getAllFilms())
            notFullFilmAdapter.updateValue(model.getNotFullFilms())
            fullFilmAdapter.updateValue(model.getFullFilms())
        })

        val chipShowNotFull = findViewById<Chip>(R.id.show_not_full_films_chip)
        val chipShowAll = findViewById<Chip>(R.id.show_all_films_chip)
        val chipShowFull = findViewById<Chip>(R.id.show_full_films_chip)

        checkedChip = chipShowAll.id
        chipShowAll.setChipBackgroundColorResource(R.color.orange)
        chipShowAll.setTextColor(ContextCompat.getColor(this, R.color.white))

        chipShowNotFull.setOnClickListener {
            if (checkedChip != chipShowNotFull.id) {
                chipShowAll.setChipBackgroundColorResource(R.color.secondary_variant_grey)
                chipShowFull.setChipBackgroundColorResource(R.color.secondary_variant_grey)
                chipShowNotFull.setChipBackgroundColorResource(R.color.orange)
                chipShowNotFull.setTextColor(ContextCompat.getColor(this, R.color.white))
                chipShowAll.setTextColor(ContextCompat.getColor(this, R.color.black))
                chipShowFull.setTextColor(ContextCompat.getColor(this, R.color.black))
                chipShowNotFull.isCloseIconVisible = false
                recyclerView.adapter = notFullFilmAdapter
                checkedChip = chipShowNotFull.id
            }
        }

        chipShowAll.setOnClickListener {
            if (chipShowAll.id != checkedChip) {
                chipShowFull.setChipBackgroundColorResource(R.color.secondary_variant_grey)
                chipShowNotFull.setChipBackgroundColorResource(R.color.secondary_variant_grey)
                chipShowAll.setChipBackgroundColorResource(R.color.orange)
                chipShowNotFull.setTextColor(ContextCompat.getColor(this, R.color.black))
                chipShowAll.setTextColor(ContextCompat.getColor(this, R.color.white))
                chipShowFull.setTextColor(ContextCompat.getColor(this, R.color.black))
                chipShowAll.isCloseIconVisible = false
                recyclerView.adapter = allFilmAdapter
                checkedChip = chipShowAll.id
            }
        }

        chipShowFull.setOnClickListener {
            if (chipShowFull.id != checkedChip) {
                chipShowAll.setChipBackgroundColorResource(R.color.secondary_variant_grey)
                chipShowNotFull.setChipBackgroundColorResource(R.color.secondary_variant_grey)
                chipShowFull.setChipBackgroundColorResource(R.color.orange)
                chipShowNotFull.setTextColor(ContextCompat.getColor(this, R.color.black))
                chipShowAll.setTextColor(ContextCompat.getColor(this, R.color.black))
                chipShowFull.setTextColor(ContextCompat.getColor(this, R.color.white))
                chipShowFull.isCloseIconVisible = false
                recyclerView.adapter = fullFilmAdapter
                checkedChip = chipShowFull.id
            }
        }

    }

    override fun onStart() {
        super.onStart()
        model.loadFilms()
    }


}