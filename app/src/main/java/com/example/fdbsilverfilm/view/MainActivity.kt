package com.example.fdbsilverfilm.view

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.fragment.app.Fragment

import com.example.fdbsilverfilm.R
import com.example.fdbsilverfilm.database.AppDatabase
import com.example.fdbsilverfilm.manager.SharedPreferencesManager
import androidx.appcompat.app.AppCompatActivity
import com.example.fdbsilverfilm.manager.DatabaseManager
import com.example.fdbsilverfilm.model.Film
import com.example.fdbsilverfilm.repository.DatabaseRepository
import com.example.fdbsilverfilm.viewmodel.MainActivityViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loading = findViewById<ProgressBar>(R.id.mainActivityLoading)
        loading.visibility = ProgressBar.VISIBLE

        DatabaseManager.initDatabase(this)

        val model = MainActivityViewModel()
        model.getFilms().observe(this, {
            if (it.count() > 0) {
                if (SharedPreferencesManager.loadCurrentFilm(this) != -1) {
                    showFragment(NoFilmFragment())
                } else {
                    showFragment(FilmFragment())
                }
            } else {
                showFragment(NoFilmListFragment())
            }
            loading.visibility = ProgressBar.GONE
        })

        Log.d("main", "onCreate: ${SharedPreferencesManager.loadCurrentFilm(this)}")

        model.loadFilms()


    }

    private fun showFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.fragmentContainerView, fragment)
        fragmentManager.commit()
    }
}