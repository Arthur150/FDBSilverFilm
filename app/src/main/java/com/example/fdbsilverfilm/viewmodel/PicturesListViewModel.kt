package com.example.fdbsilverfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fdbsilverfilm.manager.DatabaseManager
import com.example.fdbsilverfilm.manager.SharedPreferencesManager
import com.example.fdbsilverfilm.model.Film
import com.example.fdbsilverfilm.model.Picture
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PicturesListViewModel(private val filmId : Int) : ViewModel() {
    private val film = MutableLiveData<Film>()

    fun getFilm(): LiveData<Film> {
        return film
    }

    fun loadFilm() {
        if (filmId != -1) {
            viewModelScope.launch(Dispatchers.IO) {
                film.postValue(DatabaseManager.repository.getFilmByID(filmId))
            }
        }
    }

}