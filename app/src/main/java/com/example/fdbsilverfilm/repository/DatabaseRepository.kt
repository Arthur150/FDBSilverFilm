package com.example.fdbsilverfilm.repository

import com.example.fdbsilverfilm.database.DatabaseDAO
import com.example.fdbsilverfilm.model.Film

class DatabaseRepository(private val databaseDAO: DatabaseDAO) {
    fun insertAllFilm(film: List<Film>) = databaseDAO.addAllFilm(film)
    fun getAllFilm(): List<Film> = databaseDAO.getAllFilm()

    fun insertFilm(film: Film) = databaseDAO.addFilm(film)
    fun modifyFilm(film: Film) = databaseDAO.addFilm(film)
    fun deleteFilm(film: Film) = databaseDAO.deleteFilm(film)
    fun getLastFilmId() = databaseDAO.getLastFilmId()

    // passer en live data
    fun getFilmByID(id: Int): Film = databaseDAO.getFilmByID(id)

    fun insertPictureOfFilm(film: Film) = databaseDAO.updatePictureOfFilm(film)
    fun modifyPictureOfFilm(film: Film) = databaseDAO.updatePictureOfFilm(film)
    fun deletePictureOfFilm(film: Film) = databaseDAO.updatePictureOfFilm(film)
}

