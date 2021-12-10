package com.example.fdbsilverfilm.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.widget.*
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.fdbsilverfilm.R
import com.example.fdbsilverfilm.manager.SharedPreferencesManager
import com.example.fdbsilverfilm.model.Globals
import com.example.fdbsilverfilm.model.Meta
import com.example.fdbsilverfilm.viewmodel.PictureAddViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import android.location.Criteria
import android.util.Log
import java.lang.Exception


class AddPictureActivity : AppCompatActivity() {
    private var location: Location? = null


    private lateinit var focal: EditText
    private lateinit var lens: EditText
    private lateinit var opening: EditText
    private lateinit var time: EditText
    private lateinit var title: EditText


    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_picture)

        if (!PermissionsManager.checkPermissions(this)) {
            PermissionsManager.requestPermissions(this)
        } else {
            loadCoordinates()
        }

       
        focal = findViewById(R.id.addPictureFocal)
        lens = findViewById(R.id.addPictureLens)
        opening = findViewById(R.id.addPictureOpening)
        time = findViewById(R.id.addPictureTime)
        title = findViewById(R.id.addPictureTitle)

        val modeSpinner = findViewById<Spinner>(R.id.addPictureMode)
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
                        latitude = location?.latitude ?: 0.0,
                        longitude = location?.longitude ?: 0.0
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
                }
            }
        })
    }

    @SuppressLint("MissingPermission")
    private fun loadCoordinates() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation.addOnSuccessListener { location = it }
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
            lens.error = getString(R.string.check_form_field)
            isOk = false
        } else {
            lens.error = null
        }

        if (title.text.isEmpty() || title.text.isBlank()) {
            title.error = getString(R.string.check_form_field)
            isOk = false
        } else {
            title.error = null
        }

        return isOk
    }
}

