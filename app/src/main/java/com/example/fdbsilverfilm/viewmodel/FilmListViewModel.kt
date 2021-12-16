package com.example.fdbsilverfilm.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fdbsilverfilm.manager.DatabaseManager
import com.example.fdbsilverfilm.manager.SharedPreferencesManager
import com.example.fdbsilverfilm.model.Film
import com.example.fdbsilverfilm.view.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FilmListViewModel(private val context: Context) : ViewModel() {
    private val films: MutableLiveData<List<Film>> = MutableLiveData<List<Film>>()

    private var filmList: ArrayList<Film> = ArrayList()

    fun getFilms(): LiveData<List<Film>> {
        return films
    }


    fun loadFilms() {
        viewModelScope.launch(Dispatchers.IO) {
            films.postValue(DatabaseManager.repository.getAllFilm())
        }
    }

    fun getAllFilms(): List<Film> {
        return ArrayList<Film>(films.value ?: emptyList()).toList()
    }

    fun getNotFullFilms(): List<Film> {
        val suppList = ArrayList<Film>()
        val tmp = ArrayList<Film>(films.value ?: emptyList())
        for (film in tmp) {
            if (film.isClose) {
                suppList.add(film)
            }
        }
        tmp.removeAll(suppList.toSet())

        return tmp.toList()
    }

    fun getFullFilms(): List<Film> {
        if (films.value != null) {
            val suppList = ArrayList<Film>()
            val tmp = ArrayList<Film>(films.value ?: emptyList())
            for (film in tmp) {
                if (!film.isClose) {
                    suppList.add(film)
                }
            }
            tmp.removeAll(suppList.toSet())
            return tmp.toList()
        }
        return emptyList()
    }

    fun deleteFilm(film: Film) {
        viewModelScope.launch(Dispatchers.IO) {
            DatabaseManager.repository.deleteFilm(film)
            filmList.clear()
            loadFilms()

            if (SharedPreferencesManager.loadCurrentFilm(context) == film.id) {
                SharedPreferencesManager.saveCurrentFilm(context, -1)

                val intent = Intent(context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                context.startActivity(intent)
            }
        }
    }
}