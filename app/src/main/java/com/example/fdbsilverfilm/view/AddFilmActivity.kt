package com.example.fdbsilverfilm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.fdbsilverfilm.R
import com.example.fdbsilverfilm.model.Film

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

        val filmToEdit: Film? = intent.getSerializableExtra("filmToEdit") as Film

        ArrayAdapter.createFromResource(
            this,
            R.array.film_types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            if(filmToEdit != null){
                spinner.setSelection(adapter.getPosition(filmToEdit.type))
            }
        }


        if (filmToEdit != null) {

            iso.setText(filmToEdit.iso.toString())
            nbPoses.setText(filmToEdit.nbPoses.toString())
            brand.setText(filmToEdit.brand.toString())
            name.setText(filmToEdit.name.toString())


            button.setOnClickListener {

                if (!brand.text.isEmpty() && !iso.text.isEmpty() && !nbPoses.text.isEmpty()) {

                    filmToEdit.name = name.text.toString()
                    filmToEdit.brand = brand.text.toString()
                    filmToEdit.iso = iso.text.toString().toInt()
                    filmToEdit.type = spinner.selectedItem.toString()
                    filmToEdit.nbPoses = nbPoses.text.toString().toInt()

                    Log.d("addFilm", "onCreate: $filmToEdit")
                } else {
                    Toast.makeText(this, "Connard", Toast.LENGTH_SHORT).show()
                }
            }
        } else {

            button.setOnClickListener {

                if (!brand.text.isEmpty() && !iso.text.isEmpty() && !nbPoses.text.isEmpty()) {
                    val film = Film(
                        1,
                        name.text.toString(),
                        brand.text.toString(),
                        iso.text.toString().toInt(),
                        spinner.selectedItem.toString(),
                        nbPoses.text.toString().toInt()
                    )

                    Log.d("addFilm", "onCreate: $film")
                } else {
                    Toast.makeText(this, "Connard", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}