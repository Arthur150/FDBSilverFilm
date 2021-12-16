package com.example.fdbsilverfilm.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fdbsilverfilm.manager.DatabaseManager
import com.example.fdbsilverfilm.model.Film
import com.example.fdbsilverfilm.model.Meta
import com.example.fdbsilverfilm.model.Picture
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class PictureAddViewModel(private val filmId: Int, private val context: Context) : ViewModel() {
    private val film = MutableLiveData<Film>()
    var location: Location? = null


    fun getFilm(): LiveData<Film> {
        return film
    }


    @SuppressLint("MissingPermission")
    fun loadFilm() {
        viewModelScope.launch(Dispatchers.IO) {
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
            fusedLocationClient.lastLocation.addOnSuccessListener { location = it }

            film.postValue(DatabaseManager.repository.getFilmByID(filmId))
        }
    }

    fun addPicture(pictureName: String, imageString: String, meta: Meta) {

        val idPicture = film.value?.pictures?.size

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm")
        val currentDate = sdf.format(Date())

        if (filmId != -1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                idPicture?.let {
                    Picture(
                        id = it,
                        id_film = filmId,
                        title = pictureName,
                        date = currentDate,
                        preview = imageString,
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