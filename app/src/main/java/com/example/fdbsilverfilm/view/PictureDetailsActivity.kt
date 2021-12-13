package com.example.fdbsilverfilm.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fdbsilverfilm.R
import com.example.fdbsilverfilm.model.Globals
import com.example.fdbsilverfilm.model.Picture
import com.example.fdbsilverfilm.viewmodel.PictureDetailsViewModel

class PictureDetailsActivity : AppCompatActivity() {
    var vm: PictureDetailsViewModel? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_details)
        vm =
            PictureDetailsViewModel(intent.getSerializableExtra(Globals.PICTURE_EXTRA_TAG) as Picture)

        // [ title, focal, opening, laying time, mode, lens, date, coordinates ]
        val textViews = ArrayList<TextView>()
        textViews.add(findViewById(R.id.picture_details_title))
        textViews.add(findViewById(R.id.focal_value_label))
        textViews.add(findViewById(R.id.opening_value_label))
        textViews.add(findViewById(R.id.laying_time_value_label))
        textViews.add(findViewById(R.id.mode_value_label))
        textViews.add(findViewById(R.id.lens_value_label))
        textViews.add(findViewById(R.id.date_value_label))
        textViews.add(findViewById(R.id.coordinates_value_label))

        for (i in 0 until textViews.size) {
            textViews[i].text =
                if (vm?.data?.get(i).isNullOrEmpty()) getString(R.string.NR) else vm?.data?.get(i)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }
}