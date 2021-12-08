package com.example.fdbsilverfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fdbsilverfilm.model.Film
import com.example.fdbsilverfilm.model.Meta
import com.example.fdbsilverfilm.model.Picture

class FilmListViewModel : ViewModel() {
    private val films: MutableLiveData<List<Film>> = MutableLiveData<List<Film>>()

    private val filmList: ArrayList<Film> = ArrayList()

    init {
        loadFilms()
    }


    fun getFilms(): LiveData<List<Film>> {
        return films
    }

    fun loadFilms(){
        //TODO import films from database

        val pictures = ArrayList<Picture>()
        pictures.add(Picture(0,0,"test","test", Meta(10f,5f,5.0,"test","test")))
        pictures.add(Picture(0,0,"test","test", Meta(10f,5f,5.0,"test","test")))
        pictures.add(Picture(0,0,"test","test", Meta(10f,5f,5.0,"test","test")))
        pictures.add(Picture(0,0,"test","test", Meta(10f,5f,5.0,"test","test")))
        pictures.add(Picture(0,0,"test","test", Meta(10f,5f,5.0,"test","test")))

        filmList.add(Film(null,"Romaric la pute","kodak",400,"Color",20))
        filmList.add(Film(null,"Romaric la pute","kodak",400,"Color",20,pictures))
        filmList.add(Film(null,"Romaric la pute","kodak",400,"Color",20))
        filmList.add(Film(null,"Romaric la pute","kodak",400,"Color",20,pictures))
        filmList.add(Film(null,"Romaric la pute","kodak",400,"Color",20))

        val tempList = List(5) {
            filmList[it]
        }

        films.postValue(tempList)
    }
}