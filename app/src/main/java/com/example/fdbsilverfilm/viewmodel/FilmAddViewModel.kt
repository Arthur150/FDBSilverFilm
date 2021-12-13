package com.example.fdbsilverfilm.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fdbsilverfilm.manager.DatabaseManager
import com.example.fdbsilverfilm.manager.SharedPreferencesManager
import com.example.fdbsilverfilm.model.Film
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FilmAddViewModel(private val context: Context, val filmId: Int?) : ViewModel() {

    private val film = MutableLiveData<Film>()

    private var filmToSave: Film? = null

    fun getFilm(): LiveData<Film> {
        return film
    }

    //call loadFilm before this function
    fun setFilm(iso: Int, nbPoses: Int, brand: String, type: String, name: String = "",  cameraName: String) {
        if (filmId == null || filmToSave == null) {
            filmToSave = Film(null, name, brand, iso, type, nbPoses, cameraName)
        } else {
            filmToSave?.name = name
            filmToSave?.brand = brand
            filmToSave?.iso = iso
            filmToSave?.type = type
            filmToSave?.nbPoses = nbPoses
            film?.cameraName = cameraName
        }

        viewModelScope.launch(Dispatchers.IO) {
            filmToSave?.let { DatabaseManager.repository.insertFilm(it) }
            if (filmId == null) {
                SharedPreferencesManager.saveCurrentFilm(
                    context,
                    DatabaseManager.repository.getLastFilmId()
                )
            } else {
                SharedPreferencesManager.saveCurrentFilm(
                    context,
                    filmId
                )
            }
        }

    }

    fun loadFilm() {
        if (filmId != null) {
            viewModelScope.launch(Dispatchers.IO) {
                filmToSave = filmId?.let { DatabaseManager.repository.getFilmByID(it) }
                film.postValue(filmToSave?.let { filmToSave })
            }
        }
    }

}