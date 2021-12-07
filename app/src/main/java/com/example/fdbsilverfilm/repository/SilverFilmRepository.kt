package com.example.fdbsilverfilm.repository

import androidx.lifecycle.LiveData
import com.example.fdbsilverfilm.database.SilverFilmDAO
import com.example.fdbsilverfilm.model.Film

class SilverFilmRepository(private val silverFilmDAO: SilverFilmDAO) {
    suspend fun insertFilm(film: Film) = silverFilmDAO.addAllFilms(film)

    fun getAllFilm(): LiveData<List<Film>> = silverFilmDAO.getAllFilm()
}