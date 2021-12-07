package com.example.fdbsilverfilm.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fdbsilverfilm.model.Film

@Dao
interface SilverFilmDAO {

    //Film
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFilm(film: Film)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllFilm(film: List<Film>)

    @Query("SELECT * FROM Films")
    fun getAllFilm(): List<Film>
}