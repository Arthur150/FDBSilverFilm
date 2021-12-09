package com.example.fdbsilverfilm.view

import android.annotation.SuppressLint
import android.location.Criteria
import android.location.LocationManager
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.fdbsilverfilm.R
import com.example.fdbsilverfilm.manager.PermissionsManager
import com.example.fdbsilverfilm.manager.SharedPreferencesManager
import com.example.fdbsilverfilm.model.Film
import com.example.fdbsilverfilm.model.Globals
import com.example.fdbsilverfilm.model.Meta
import com.example.fdbsilverfilm.viewmodel.PictureAddViewModel

class AddPictureActivity : AppCompatActivity() {
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_picture)


        val focal = findViewById<EditText>(R.id.addPictureFocal)
        val lens = findViewById<EditText>(R.id.addPictureLens)
        val modeSpinner = findViewById<Spinner>(R.id.addPictureMode)
        val opening = findViewById<EditText>(R.id.addPictureOpening)
        val time = findViewById<EditText>(R.id.addPictureTime)
        val title = findViewById<EditText>(R.id.addPictureTitle)

        val button = findViewById<Button>(R.id.addPictureButton)


        val vm = PictureAddViewModel()

        val film = intent.getSerializableExtra(Globals.FILM_EXTRA_TAG) as Film


        ArrayAdapter.createFromResource(
            this,
            R.array.picture_mode,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            modeSpinner.adapter = adapter


            val metaSharedPreferences = SharedPreferencesManager.loadCurrentMeta(this)

            if (metaSharedPreferences != null) {
                focal.setText(metaSharedPreferences.focal.toString())
                lens.setText(metaSharedPreferences.lens)
                opening.setText(metaSharedPreferences.opening.toString())
                time.setText(metaSharedPreferences.time.toString())

                modeSpinner.setSelection(adapter.getPosition(metaSharedPreferences.mode))
            }
        }


        button.setOnClickListener {
            if (focal.text.isNotEmpty() && lens.text.isNotEmpty() && opening.text.isNotEmpty() && time.text.isNotEmpty() && title.text.isNotEmpty()) {
                if (!PermissionsManager.checkPermissions(this)) {
                    PermissionsManager.requestPermissions(this)
                }

                val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?
                val location = locationManager?.getLastKnownLocation(
                    locationManager.getBestProvider(
                        Criteria(),
                        true
                    )!!
                )

                val meta = Meta(
                    focal = focal.text.toString().toFloat(),
                    opening = opening.text.toString().toFloat(),
                    time = time.text.toString().toDouble(),
                    mode = modeSpinner.selectedItem.toString(),
                    lens = lens.text.toString(),
                    coordinates = location
                )

                vm.addPicture(
                    film = film,
                    pictureName = title.text.toString(),
                    meta = meta
                )

                //Save the last config Meta
                SharedPreferencesManager.saveCurrentMeta(this, meta)

            } else {
                Toast.makeText(this, getString(R.string.checkForm), Toast.LENGTH_SHORT).show()
            }
        }
    }
}