package com.example.fdbsilverfilm.view

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.fdbsilverfilm.R
import com.example.fdbsilverfilm.model.Globals
import com.example.fdbsilverfilm.viewmodel.FilmViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FilmFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_film, container, false)
        val model = FilmViewModel(requireContext())
        model.loadFilm()

        val nameTextView = view.findViewById<TextView>(R.id.filmFragmentFilmName)
        val nameCameraTextView = view.findViewById<TextView>(R.id.filmFragmentFilmCameraName)
        val isoTextView = view.findViewById<TextView>(R.id.filmFragmentFilmISO)
        val brandTextView = view.findViewById<TextView>(R.id.filmFragmentFilmBrand)
        val pictureCountTextView = view.findViewById<TextView>(R.id.filmFragmentCountPicture)
        val countPictureLabel = view.findViewById<TextView>(R.id.filmFragmentFilmCountPictureLabel)

        val takePictureButton = view.findViewById<Button>(R.id.filmFragmentTakePictureButton)
        val changeFilmButton = view.findViewById<Button>(R.id.filmFragmentChangeFilmButton)
        val showPicturesButton = view.findViewById<Button>(R.id.filmFragmentShowPictureButton)

        val closeFilmButton = view.findViewById<Button>(R.id.fragmentFilmCloseButton)
        val editButton = view.findViewById<FloatingActionButton>(R.id.fragmentFilmEditButton)
        editButton.imageTintList =  ColorStateList.valueOf(getColor(requireContext(),R.color.secondary_variant_grey))

        model.getFilmValue()?.let { film ->
            if (film.pictures.size >= film.nbPoses) {
                pictureCountTextView.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.red
                    )
                )
                countPictureLabel.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.red
                    )
                )
            }

            if (!film.isClose) {
                takePictureButton.isEnabled = true
                takePictureButton.setTextColor(getColor(requireContext(), R.color.white))
                takePictureButton.setBackgroundColor(getColor(requireContext(), R.color.orange))
                closeFilmButton.isEnabled = true
            } else {
                closeFilmButton.text = getString(R.string.film_already_archived)
            }


            if (film.pictures.size > 0) {
                showPicturesButton.isEnabled = true
                showPicturesButton.setTextColor(getColor(requireContext(), R.color.white))
                showPicturesButton.setBackgroundColor(getColor(requireContext(), R.color.orange))
            }
        }

        showPicturesButton.setOnClickListener {
            val intent = Intent(requireContext(), PicturesListActivity::class.java)
            intent.putExtra(Globals.FILM_EXTRA_TAG, model.getFilmValue()?.id)
            startActivity(intent)
        }


        model.getFilm().observe(viewLifecycleOwner, { film ->
            if (film != null) {
                nameTextView.text = film.name
                isoTextView.text = film.iso.toString()
                brandTextView.text = film.brand
                nameCameraTextView.text = film.cameraName
                pictureCountTextView.text = "${film.pictures.count()}/${film.nbPoses}"
                showPicturesButton.isEnabled = film.pictures.size > 0


                editButton.setOnClickListener {
                    val intent = Intent(context, AddFilmActivity::class.java)
                    intent.putExtra(Globals.FILM_ID_EXTRA_TAG, film.id)
                        .putExtra(Globals.MAIN_EXTRA_TAG, true)
                    startActivity(intent)
                }

                closeFilmButton.setOnClickListener {

                    AlertDialog.Builder(requireContext())
                        .setIcon(R.drawable.ic_film_roll_svgrepo_com)
                        .setTitle(getString(R.string.archive_film_title))
                        .setMessage(getString(R.string.archive_film_ask))
                        .setPositiveButton(getString(R.string.archive_film)) { _, _ ->
                            model.setIsClose(film)
                            startFilmListActivity()
                        }
                        .setNeutralButton(getString(R.string.cancel)) { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create().show()
                }


                if (film.pictures.size >= film.nbPoses) {
                    pictureCountTextView.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.red
                        )
                    )
                    countPictureLabel.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.red
                        )
                    )
                }

                if (!film.isClose) {
                    takePictureButton.isEnabled = true
                    takePictureButton.setTextColor(getColor(requireContext(), R.color.white))
                    takePictureButton.setBackgroundColor(getColor(requireContext(), R.color.orange))
                    closeFilmButton.isEnabled = true
                } else {

                    closeFilmButton.text = getString(R.string.film_already_archived)
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
            startFilmListActivity()
        }

        return view
    }

    private fun startFilmListActivity() {
        val intent = Intent(requireContext(), FilmListActivity::class.java)
        //intent.putExtra("filter", Globals.NOT_FULL_FILTER)
        startActivity(intent)
    }

}