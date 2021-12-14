package com.example.fdbsilverfilm.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
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
    private lateinit var title: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_film)

        title = findViewById(R.id.addFilmTextView)
        iso = findViewById(R.id.addFilmIso)
        nbPoses = findViewById(R.id.addFilmNbPoses)
        brand = findViewById(R.id.addFilmBrand)
        val spinner = findViewById<Spinner>(R.id.addFilmSpinnerType)
        name = findViewById(R.id.addFilmName)
        val button = findViewById<Button>(R.id.addFilmSaveButton)

        cameraName = findViewById(R.id.addFilmCameraName)

       
        val vm = FilmAddViewModel(this, intent.getSerializableExtra(Globals.FILM_ID_EXTRA_TAG) as Int?)
        vm.loadFilm()

        ArrayAdapter.createFromResource(
            this,
            R.array.film_types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter

            vm.getFilm().observe(this,{ film ->
                title.setText(R.string.edit_film)
                spinner.setSelection(adapter.getPosition(film.type))
                iso.setText(film.iso.toString())
                nbPoses.setText(film.nbPoses.toString())
                brand.setText(film.brand)
                name.setText(film.name)
                cameraName.setText(film.cameraName)

            })

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

                if (intent.getBooleanExtra(Globals.MAIN_EXTRA_TAG, false)) {
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