package com.example.fdbsilverfilm.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.fdbsilverfilm.R
import com.example.fdbsilverfilm.manager.SharedPreferencesManager
import com.example.fdbsilverfilm.model.Globals
import com.example.fdbsilverfilm.model.Meta
import com.example.fdbsilverfilm.viewmodel.PictureAddViewModel

class AddPictureActivity : AppCompatActivity() {
    private var location: Location? = null

    lateinit var focal: EditText
    lateinit var lens: EditText
    lateinit var opening: EditText
    lateinit var time: EditText

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_picture)

        focal = findViewById(R.id.addPictureFocal)
        lens = findViewById(R.id.addPictureLens)
        opening = findViewById(R.id.addPictureOpening)
        time = findViewById(R.id.addPictureTime)
        val modeSpinner = findViewById<Spinner>(R.id.addPictureMode)
        val title = findViewById<EditText>(R.id.addPictureTitle)

        val button = findViewById<Button>(R.id.addPictureButton)


        val vm = PictureAddViewModel(intent.getIntExtra(Globals.FILM_EXTRA_TAG, -1))
        vm.loadFilm()

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

        val requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    loadCoordinates()
                } else {
                    finish()
                }
            }

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_DENIED
        ) {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        }

        vm.getFilm().observe(this, {
            button.isEnabled = true
            button.setOnClickListener {
                if (checkForm()) {

                    val meta = Meta(
                        focal = focal.text.toString().toFloat(),
                        opening = opening.text.toString().toFloat(),
                        time = time.text.toString().toDouble(),
                        mode = modeSpinner.selectedItem.toString(),
                        lens = lens.text.toString(),
                        coordinates = location
                    )

                    vm.addPicture(
                        pictureName = title.text.toString(),
                        meta = meta
                    )

                    //Save the last config Meta
                    SharedPreferencesManager.saveCurrentMeta(this, meta)

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    Toast.makeText(this, getString(R.string.checkForm), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    @SuppressLint("MissingPermission")
    private fun loadCoordinates() {
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?
        location = locationManager?.getLastKnownLocation(
            locationManager.getBestProvider(
                Criteria(),
                true
            )!!
        )
    }

    private fun checkForm(): Boolean {
        var isOk = true


        if (focal.text.isEmpty() || focal.text.isBlank()) {
            focal.error = getString(R.string.check_form_field)
            isOk = false
        } else {
            if (!Globals.regexDecimal(focal.text.toString())) {
                focal.error = getString(R.string.check_form_regex_decimal)
                isOk = false
            } else {
                focal.error = null
            }
        }

        if (opening.text.isEmpty() || opening.text.isBlank()) {
            opening.error = getString(R.string.check_form_field)
            isOk = false
        } else {
            if (!Globals.regexDecimal(opening.text.toString())) {
                opening.error = getString(R.string.check_form_regex_decimal)
                isOk = false
            } else {
                opening.error = null
            }
        }

        if (time.text.isEmpty() || time.text.isBlank()) {
            time.error = getString(R.string.check_form_field)
            isOk = false
        } else {
            if (!Globals.regexDecimal(time.text.toString())) {
                time.error = getString(R.string.check_form_regex_decimal)
                isOk = false
            } else {
                time.error = null
            }
        }

        if (lens.text.isEmpty() || lens.text.isBlank()) {
            lens.error = getString(R.string.check_form_regex_decimal)
            isOk = false
        } else {
            isOk = false
        }

        return isOk
    }
}