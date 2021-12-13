package com.example.fdbsilverfilm.database

import androidx.room.*
import com.example.fdbsilverfilm.model.Film

@Dao
interface DatabaseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFilm(film: Film)

    @Delete
    fun deleteFilm(film: Film)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllFilm(film: List<Film>)

    @Query("SELECT * FROM Films")
    fun getAllFilm(): List<Film>

    @Query("SELECT * FROM Films WHERE id = :id")
    fun getFilmByID(id: Int): Film

    @Update
    fun updatePictureOfFilm(film: Film)

    @Query("SELECT MAX(id) FROM Films")
    fun getLastFilmId(): Int
}