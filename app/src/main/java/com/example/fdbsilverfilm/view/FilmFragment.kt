package com.example.fdbsilverfilm.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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

        val nameTextView = view.findViewById<TextView>(R.id.filmFragmentFilmName)
        val isoTextView = view.findViewById<TextView>(R.id.filmFragmentFilmISO)
        val brandTextView = view.findViewById<TextView>(R.id.filmFragmentFilmBrand)
        val pictureCountTextView = view.findViewById<TextView>(R.id.filmFragmentCountPicture)

        val takePictureButton = view.findViewById<Button>(R.id.filmFragmentTakePictureButton)
        takePictureButton.setOnClickListener {
            //TODO go to addPicture
            Toast.makeText(requireContext(),R.string.not_implemented_yet,Toast.LENGTH_SHORT).show()
        }

        val changeFilmButton = view.findViewById<Button>(R.id.filmFragmentChangeFilmButton)
        changeFilmButton.setOnClickListener {
            val intent = Intent(requireContext(),FilmListActivity::class.java)
            intent.putExtra("filter",Globals.NOT_FULL_FILTER)
            startActivity(intent)
        }



        return view
    }

}