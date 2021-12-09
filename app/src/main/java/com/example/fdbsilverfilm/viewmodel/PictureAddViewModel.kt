package com.example.fdbsilverfilm.viewmodel

import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fdbsilverfilm.manager.DatabaseManager
import com.example.fdbsilverfilm.model.Film
import com.example.fdbsilverfilm.model.Meta
import com.example.fdbsilverfilm.model.Picture
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class PictureAddViewModel(private val filmId: Int) : ViewModel() {
    private val film = MutableLiveData<Film>()

    fun getFilm(): LiveData<Film> {
        return film
    }

    fun loadFilm() {
        viewModelScope.launch(Dispatchers.IO) {
            film.postValue(DatabaseManager.repository.getFilmByID(filmId))
        }
    }

    fun addPicture(pictureName: String, meta: Meta) {

        val idPicture = film.value?.pictures?.size

        if (filmId != -1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                idPicture?.let {
                    Picture(
                        id = it,
                        id_film = filmId,
                        title = pictureName,
                        date = LocalDateTime.now().toString(),
                        meta = meta
                    )
                }?.let {
                    film.value?.pictures?.add(
                        it
                    )
                }

                viewModelScope.launch(Dispatchers.IO) {
                    film.value?.let {
                        DatabaseManager.repository.insertPictureOfFilm(
                            it
                        )
                    }
                }
            }
        }
    }
}