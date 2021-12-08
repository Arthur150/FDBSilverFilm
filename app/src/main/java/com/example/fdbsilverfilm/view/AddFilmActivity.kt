package com.example.fdbsilverfilm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.fdbsilverfilm.R
import com.example.fdbsilverfilm.model.Film
import com.example.fdbsilverfilm.viewmodel.FilmAddViewModel

class AddFilmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_film)


        val iso = findViewById<EditText>(R.id.addFilmIso)
        val nbPoses = findViewById<EditText>(R.id.addFilmNbPoses)
        val brand = findViewById<EditText>(R.id.addFilmBrand)
        val spinner = findViewById<Spinner>(R.id.addFilmSpinnerType)
        val name = findViewById<EditText>(R.id.addFilmName)
        val button = findViewById<Button>(R.id.addFilmSaveButton)

        val vm = FilmAddViewModel(intent.getSerializableExtra("filmToEdit") as Film)

        ArrayAdapter.createFromResource(
            this,
            R.array.film_types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter

            spinner.setSelection(adapter.getPosition(vm.film.type))
            iso.setText(vm.film.iso.toString())
            nbPoses.setText(vm.film.nbPoses.toString())
            brand.setText(vm.film.brand)
            name.setText(vm.film.name.toString())
        }

            button.setOnClickListener {

                if (brand.text.isNotEmpty() && iso.text.isNotEmpty() && nbPoses.text.isNotEmpty()) {

                    vm.setFilm(
                        iso.text.toString().toInt(),
                        nbPoses.text.toString().toInt(),
                        brand.text.toString(),
                        spinner.selectedItem.toString(),
                        name.text.toString()
                    )

                    Log.d("addFilm", "onCreate: ${vm.film}")
                } else {
                    Toast.makeText(this, "Connard", Toast.LENGTH_SHORT).show()
                }
            }
    }
}