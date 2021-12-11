package com.example.fdbsilverfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fdbsilverfilm.manager.DatabaseManager
import com.example.fdbsilverfilm.model.Film
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    private val films = MutableLiveData<List<Film>>()

    fun getFilms(): LiveData<List<Film>> {
        return films
    }

    fun loadFilms() {
        viewModelScope.launch(Dispatchers.IO) {
            films.postValue(DatabaseManager.repository.getAllFilm())
        }
    }
}