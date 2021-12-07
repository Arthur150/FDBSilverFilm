package com.example.fdbsilverfilm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.fdbsilverfilm.R
import com.example.fdbsilverfilm.model.Picture
import com.example.fdbsilverfilm.viewmodel.PictureDetailsViewModel

const val PICTURE_EXTRA_TAG = "picture"

class PictureDetailsActivity : AppCompatActivity() {
    var vm : PictureDetailsViewModel? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_details)
        vm = PictureDetailsViewModel(intent.getSerializableExtra(PICTURE_EXTRA_TAG) as Picture)

        val title = findViewById<TextView>(R.id.picture_details_title).let {
            it.text = if (vm?.picture?.title.isNullOrEmpty()) getString(R.string.NR) else vm?.picture?.title
        }

        val focal = findViewById<TextView>(R.id.focal_value_label).let {

            it.text = if (vm?.picture?.meta?.focal != 0.0f) getString(R.string.NR) else vm?.picture?.meta?.focal.toString()
        }

        val opening = findViewById<TextView>(R.id.opening_value_label).let {

            it.text = if (vm?.picture?.meta?.opening != 0.0f) getString(R.string.NR) else vm?.picture?.meta?.opening.toString()
        }

        val time = findViewById<TextView>(R.id.laying_time_value_label).let {

            it.text = if (vm?.picture?.meta?.time != 0.0) getString(R.string.NR) else vm?.picture?.meta?.time.toString()
        }
    }
}