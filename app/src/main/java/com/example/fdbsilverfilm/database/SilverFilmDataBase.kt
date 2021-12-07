package com.example.fdbsilverfilm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fdbsilverfilm.model.Picture

@Database(
    entities = [Picture::class],
    version = 1
)

abstract class SilverFilmDataBase : RoomDatabase() {

    abstract fun silverFilmDAO(): SilverFilmDAO

    companion object {
        @Volatile
        private var INSTANCE: SilverFilmDataBase? = null

        fun getDatabase(context: Context): SilverFilmDataBase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SilverFilmDataBase::class.java,
                    "silverFilm_DataBase"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}