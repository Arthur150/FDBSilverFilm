package com.example.fdbsilverfilm.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.fdbsilverfilm.R
import com.example.fdbsilverfilm.model.Film
import com.example.fdbsilverfilm.model.Globals
import com.example.fdbsilverfilm.viewmodel.FilmAddViewModel

class AddFilmActivity : AppCompatActivity() {

    private lateinit var iso: EditText
    private lateinit var nbPoses: EditText
    private lateinit var brand: EditText
    private lateinit var name: EditText
    private lateinit var cameraName: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_film)

        iso = findViewById(R.id.addFilmIso)
        nbPoses = findViewById(R.id.addFilmNbPoses)
        brand = findViewById(R.id.addFilmBrand)
        val spinner = findViewById<Spinner>(R.id.addFilmSpinnerType)
        name = findViewById(R.id.addFilmName)
        val button = findViewById<Button>(R.id.addFilmSaveButton)

        cameraName = findViewById(R.id.addFilmCameraName)

        val vm = FilmAddViewModel(this, intent.getSerializableExtra("filmToEdit") as Film?)

        ArrayAdapter.createFromResource(
            this,
            R.array.film_types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter

            if (vm.film != null) {
                spinner.setSelection(adapter.getPosition(vm.film?.type))
                iso.setText(vm.film?.iso.toString())
                nbPoses.setText(vm.film?.nbPoses.toString())
                brand.setText(vm.film?.brand)
                name.setText(vm.film?.name.toString())
                cameraName.setText(vm.film?.cameraName.toString())
            }
        }

        button.setOnClickListener {

            if (checkForm()) {
                vm.setFilm(
                    iso.text.toString().toInt(),
                    nbPoses.text.toString().toInt(),
                    brand.text.toString(),
                    spinner.selectedItem.toString(),
                    name.text.toString(),
                    cameraName.text.toString()
                )

                Log.d("addFilm", "onCreate: ${vm.film}")

                if (intent.getBooleanExtra("createFirst", false)) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val intent = Intent(this, FilmListActivity::class.java)
                    startActivity(intent)
                    finish()
                }

            }
        }
    }


    private fun checkForm(): Boolean {
        var isOk = true

        if (brand.text.isEmpty() || brand.text.isBlank()) {
            brand.error = getString(R.string.check_form_field)
            isOk = false
        } else {
            brand.error = null
        }

        if (iso.text.isEmpty() || iso.text.isBlank()) {
            iso.error = getString(R.string.check_form_field)
            isOk = false
        } else {
            if (!Globals.regexInt(iso.text.toString())) {
                iso.error = getString(R.string.check_form_int_regex)
                isOk = false
            } else {
                iso.error = null
            }
        }

        if (nbPoses.text.isEmpty() || nbPoses.text.isBlank()) {
            nbPoses.error = getString(R.string.check_form_field)
            isOk = false
        } else {
            if (!Globals.regexInt(nbPoses.text.toString())) {
                nbPoses.error = getString(R.string.check_form_int_regex)
                isOk = false
            } else {
                nbPoses.error = null
            }
        }

        if (cameraName.text.isEmpty() || cameraName.text.isBlank()) {
            cameraName.error = getString(R.string.check_form_field)
            isOk = false
        } else {
            cameraName.error = null
        }

        return isOk
    }
}