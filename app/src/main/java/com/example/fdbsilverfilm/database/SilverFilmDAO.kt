package com.example.fdbsilverfilm.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fdbsilverfilm.model.Film

@Dao
interface SilverFilmDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllFilms(film: Film)

    @Query("SELECT * FROM Films")
    fun getAllFilm(): LiveData<List<Film>>
}