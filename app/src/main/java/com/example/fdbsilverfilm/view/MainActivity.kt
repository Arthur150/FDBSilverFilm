package com.example.fdbsilverfilm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment

import com.example.fdbsilverfilm.R
import com.example.fdbsilverfilm.database.AppDatabase
import com.example.fdbsilverfilm.manager.SharedPreferencesManager
import com.example.fdbsilverfilm.model.Film
import com.example.fdbsilverfilm.repository.DatabaseRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dao = AppDatabase.getDatabase(this).silverFilmDAO()
        val repository = DatabaseRepository(dao)

        Log.d("main", "onCreate: ${SharedPreferencesManager.loadCurrentFilm(this)}")

        showFragment(NoFilmFragment())

    }

    private fun showFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.fragmentContainerView, fragment)
        fragmentManager.commit()
    }
}