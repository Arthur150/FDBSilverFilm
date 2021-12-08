package com.example.fdbsilverfilm.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fdbsilverfilm.model.Film

class FilmAddViewModel(var film: Film = Film(null)) : ViewModel() {

    fun setFilm(iso: Int, nbPoses: Int, brand: String, type: String, name: String? = null) {
        film.name = name
        film.brand = brand
        film.iso = iso
        film.type = type
        film.ndPoses = nbPoses
    }

}