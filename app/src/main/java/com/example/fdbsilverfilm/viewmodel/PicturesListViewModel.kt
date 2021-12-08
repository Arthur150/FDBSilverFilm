package com.example.fdbsilverfilm.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fdbsilverfilm.model.Film
import com.example.fdbsilverfilm.model.Picture

class PicturesListViewModel(private val film : Film) : ViewModel() {
    var data : List<Picture> = film.pictures

}