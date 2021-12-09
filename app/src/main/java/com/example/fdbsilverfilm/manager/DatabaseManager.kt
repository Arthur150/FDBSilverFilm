package com.example.fdbsilverfilm.manager

import android.content.Context
import com.example.fdbsilverfilm.database.AppDatabase
import com.example.fdbsilverfilm.database.DatabaseDAO
import com.example.fdbsilverfilm.repository.DatabaseRepository

object DatabaseManager {
    lateinit var repository : DatabaseRepository

    fun initDatabase(context: Context){
        val dao = AppDatabase.getDatabase(context).dao()
        repository = DatabaseRepository(dao)
    }
}