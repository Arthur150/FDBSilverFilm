package com.example.fdbsilverfilm.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.fdbsilverfilm.R
import com.example.fdbsilverfilm.model.Globals

class NoFilmFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_no_film, container, false)

        val button = view.findViewById<Button>(R.id.noFilmFragmentButton)
        button.setOnClickListener {
            val intent = Intent(requireContext(), FilmListActivity::class.java)
            intent.putExtra("filter", Globals.NOT_FULL_FILTER)
            startActivity(intent)
        }

        return view
    }
}