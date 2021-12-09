package com.example.fdbsilverfilm.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.fdbsilverfilm.R
import com.example.fdbsilverfilm.model.Globals

class NoFilmListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_no_film_list, container, false)

        val button = view.findViewById<Button>(R.id.noFilmListFragmentButton)
        button.setOnClickListener {
            val intent = Intent(requireContext(),AddFilmActivity::class.java)
            intent.putExtra("createFirst", Globals.CREATE_FIRST)
            startActivity(intent)
        }

        return view
    }


}