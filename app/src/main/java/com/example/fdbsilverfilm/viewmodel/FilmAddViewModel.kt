package com.example.fdbsilverfilm.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fdbsilverfilm.manager.DatabaseManager
import com.example.fdbsilverfilm.manager.SharedPreferencesManager
import com.example.fdbsilverfilm.model.Film
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FilmAddViewModel(private val context: Context, var film: Film?) : ViewModel() {

    fun setFilm(
        iso: Int,
        nbPoses: Int,
        brand: String,
        type: String,
        name: String = "",
        cameraName: String
    ) {
        if (film == null) {
            film = Film(null, name, brand, iso, type, nbPoses,cameraName)
        } else {
            film?.name = name
            film?.brand = brand
            film?.iso = iso
            film?.type = type
            film?.nbPoses = nbPoses
            film?.cameraName = cameraName
        }

        viewModelScope.launch(Dispatchers.IO) {
            film?.let { DatabaseManager.repository.insertFilm(it) }
            SharedPreferencesManager.saveCurrentFilm(
                context,
                DatabaseManager.repository.getLastFilmId()
            )
        }

    }

}