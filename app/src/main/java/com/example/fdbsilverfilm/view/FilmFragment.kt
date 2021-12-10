package com.example.fdbsilverfilm.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.fdbsilverfilm.R
import com.example.fdbsilverfilm.model.Globals
import com.example.fdbsilverfilm.viewmodel.FilmViewModel

class FilmFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_film, container, false)
        val model = FilmViewModel(requireContext())
        model.loadFilm()

        val nameTextView = view.findViewById<TextView>(R.id.filmFragmentFilmName)
        val isoTextView = view.findViewById<TextView>(R.id.filmFragmentFilmISO)
        val brandTextView = view.findViewById<TextView>(R.id.filmFragmentFilmBrand)
        val pictureCountTextView = view.findViewById<TextView>(R.id.filmFragmentCountPicture)

        val takePictureButton = view.findViewById<Button>(R.id.filmFragmentTakePictureButton)
        val changeFilmButton = view.findViewById<Button>(R.id.filmFragmentChangeFilmButton)

        model.getFilm().observe(viewLifecycleOwner, { film ->
            if (film != null) {
                nameTextView.text = film.name
                isoTextView.text = film.iso.toString()
                brandTextView.text = film.brand
                pictureCountTextView.text = "${film.pictures.count()}/${film.nbPoses}"
                if (film.pictures.count() < film.nbPoses) {
                    takePictureButton.isEnabled = true
                } else {
                    takePictureButton.isEnabled = false
                    AlertDialog.Builder(requireContext())
                        .setTitle(R.string.can_not_take_picture_alert).create()
                }
            }

        })

        takePictureButton.setOnClickListener {
            model.getFilmValue()?.let {
                val intent = Intent(requireContext(), AddPictureActivity::class.java)
                intent.putExtra(Globals.FILM_EXTRA_TAG, it.id)
                startActivity(intent)
            }
        }

        changeFilmButton.setOnClickListener {
            val intent = Intent(requireContext(), FilmListActivity::class.java)
            intent.putExtra("filter", Globals.NOT_FULL_FILTER)
            startActivity(intent)
        }



        return view
    }

}