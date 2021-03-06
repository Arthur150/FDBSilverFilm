package com.example.fdbsilverfilm.view

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fdbsilverfilm.R
import com.example.fdbsilverfilm.manager.DatabaseManager
import com.example.fdbsilverfilm.manager.PermissionsManager
import com.example.fdbsilverfilm.manager.SharedPreferencesManager
import com.example.fdbsilverfilm.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private var model: MainActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DatabaseManager.initDatabase(this)

        if (!PermissionsManager.checkPermissions(this)) {
            PermissionsManager.requestPermissions(this)
        }

        val loading = findViewById<ProgressBar>(R.id.mainActivityLoading)
        val textLoading = findViewById<TextView>(R.id.mainActivityLoadingTextView)
        loading.visibility = ProgressBar.VISIBLE
        textLoading.visibility = TextView.VISIBLE

        model = MainActivityViewModel()

        model?.apply {

            getFilms().observe(this@MainActivity, { films ->
                if (films.count() > 0) {
                    if (SharedPreferencesManager.loadCurrentFilm(this@MainActivity) == -1) {
                        showFragment(NoFilmFragment())
                    } else {
                        showFragment(FilmFragment())
                    }
                } else {
                    showFragment(NoFilmListFragment())
                }
                loading.visibility = ProgressBar.GONE
                textLoading.visibility = TextView.GONE
            })
        }
    }

    private fun showFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.fragmentContainerView, fragment)
        fragmentManager.commit()
    }

    override fun onStart() {
        super.onStart()
        model?.loadFilms()
    }
}