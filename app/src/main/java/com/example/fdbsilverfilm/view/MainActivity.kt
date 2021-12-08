package com.example.fdbsilverfilm.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fdbsilverfilm.R
import com.example.fdbsilverfilm.model.Film

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val intent = Intent(this, AddFilmActivity::class.java)
         intent.putExtra("filmToEdit", Film(0, "dqdqdq", "dqsdqsdqsd", 5, "Black and white", 25))
         startActivity(intent)*/

        val intent = Intent(this, AddPictureActivity::class.java)
        intent.putExtra("filmToEdit", Film(0, "dqdqdq", "dqsdqsdqsd", 5, "Black and white", 25))
        startActivity(intent)

    }
}