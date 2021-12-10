package com.example.fdbsilverfilm.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fdbsilverfilm.manager.DatabaseManager
import com.example.fdbsilverfilm.manager.SharedPreferencesManager
import com.example.fdbsilverfilm.model.Film
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FilmViewModel(private val context: Context) : ViewModel() {
    private val ldFilm = MutableLiveData<Film>()
    private var film : Film? = null


    fun getFilm(): LiveData<Film?> {
        return ldFilm
    }

    fun getFilmValue(): Film? {
        return film
    }

    fun loadFilm() {
        val filmId = SharedPreferencesManager.loadCurrentFilm(context)
        if (filmId != -1) {
            viewModelScope.launch(Dispatchers.IO) {
                film = DatabaseManager.repository.getFilmByID(filmId)
                ldFilm.postValue(DatabaseManager.repository.getFilmByID(filmId))
            }
        }
    }
}