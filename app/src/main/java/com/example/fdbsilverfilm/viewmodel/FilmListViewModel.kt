package com.example.fdbsilverfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fdbsilverfilm.manager.DatabaseManager
import com.example.fdbsilverfilm.model.Film
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FilmListViewModel : ViewModel() {
    private val films: MutableLiveData<List<Film>> = MutableLiveData<List<Film>>()

    private val filmsNotFull: MutableLiveData<List<Film>> = MutableLiveData<List<Film>>()

    private var filmList: ArrayList<Film> = ArrayList()

    fun getFilms(): LiveData<List<Film>> {
        return films
    }

    fun getFilmsNotFull(): LiveData<List<Film>> {
        return filmsNotFull
    }

    fun loadFilms() {
        viewModelScope.launch(Dispatchers.IO) {

            filmList.addAll(DatabaseManager.repository.getAllFilm())

            var tempList = List(filmList.size) {
                filmList[it]
            }

            films.postValue(tempList)

            val tempNotFull = ArrayList<Film>()
            filmList.forEach { film ->
                if (film.pictures.count() < film.nbPoses) {
                    tempNotFull.add(film)
                }
            }
            tempList = List(tempNotFull.size) {
                tempNotFull[it]
            }

            filmsNotFull.postValue(tempList)
        }
    }
}