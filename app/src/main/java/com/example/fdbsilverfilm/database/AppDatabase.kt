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
abstract class AppDatabase : RoomDatabase() {

    abstract fun dao(): DatabaseDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "silverFilm_DataBase"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}