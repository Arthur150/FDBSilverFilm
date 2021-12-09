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
    private val film = MutableLiveData<Film>()

    fun getFilm(): LiveData<Film> {
        return film
    }

    fun loadFilm() {
        val filmId = SharedPreferencesManager.loadCurrentFilm(context)
        if (filmId != -1) {
            viewModelScope.launch(Dispatchers.IO) {
                film.postValue(DatabaseManager.repository.getFilmByID(filmId))
            }
        }
    }
}