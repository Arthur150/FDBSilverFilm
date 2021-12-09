package com.example.fdbsilverfilm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fdbsilverfilm.manager.DatabaseManager
import com.example.fdbsilverfilm.model.Film
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FilmAddViewModel(var film: Film?) : ViewModel() {

    fun setFilm(iso: Int, nbPoses: Int, brand: String, type: String, name: String = "") {
        if (film == null) {
            film = Film(null, name, brand, iso, type, nbPoses)
        } else {
            film?.name = name
            film?.brand = brand
            film?.iso = iso
            film?.type = type
            film?.nbPoses = nbPoses
        }

        viewModelScope.launch(Dispatchers.IO) {
            film?.let { DatabaseManager.repository.insertFilm(it) }
        }
    }

}