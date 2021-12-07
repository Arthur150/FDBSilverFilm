package com.example.fdbsilverfilm.repository

import com.example.fdbsilverfilm.database.SilverFilmDAO
import com.example.fdbsilverfilm.model.Film

class SilverFilmRepository(private val silverFilmDAO: SilverFilmDAO) {
    fun insertAllFilm(film: List<Film>) = silverFilmDAO.addAllFilm(film)
    fun insertFilm(film: Film) = silverFilmDAO.addFilm(film)

    // passer en live data
    fun getAllFilm(): List<Film> = silverFilmDAO.getAllFilm()
}

