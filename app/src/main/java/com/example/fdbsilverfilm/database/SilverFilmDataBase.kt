package com.example.fdbsilverfilm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.fdbsilverfilm.model.Film

@Database(
    entities = [Film::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(Converters::class)
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
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}