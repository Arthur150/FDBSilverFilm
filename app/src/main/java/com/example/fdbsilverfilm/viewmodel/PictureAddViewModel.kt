package com.example.fdbsilverfilm.viewmodel

import android.os.Build
import com.example.fdbsilverfilm.model.Film
import com.example.fdbsilverfilm.model.Meta
import com.example.fdbsilverfilm.model.Picture
import java.time.LocalDateTime

class PictureAddViewModel {

    fun addPicture(film: Film, pictureName: String, meta: Meta) {

        val idPicture = film.pictures.size

        if (film.id != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                film.pictures.add(
                    Picture(
                        id= idPicture,
                        id_film = film.id,
                        title = pictureName,
                        date = LocalDateTime.now().toString(),
                        meta = meta
                    )
                )
            }
        }
    }
}