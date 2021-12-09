package com.example.fdbsilverfilm.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fdbsilverfilm.manager.SharedPreferencesManager
import com.example.fdbsilverfilm.model.Film

class FilmViewModel(private val context: Context) : ViewModel() {
    private val film = MutableLiveData<Film>()

    fun getFilm(): LiveData<Film> {
        return film
    }

    fun loadFilm() {
        val filmId = SharedPreferencesManager.loadCurrentFilm(context)
        if (filmId != -1) {
            //TODO get currentFilm and set film in viewModel
        }
    }
}