package com.example.fdbsilverfilm.model

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import java.io.ByteArrayOutputStream
import java.util.*

object Globals {
    const val PICTURE_EXTRA_TAG = "picture"
    const val FILM_EXTRA_TAG = "film"
    const val NOT_FULL_FILTER = 1
    const val MAIN_EXTRA_TAG = "main"
    const val FILM_ID_EXTRA_TAG = "filmId"
    const val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"


    fun regexInt(string: String): Boolean {
        return string.matches(Regex("^[1-9]\\d*\$"))
    }

    fun regexDecimal(string: String): Boolean {
        return string.matches(Regex("^[+-]?([0-9]+\\.?[0-9]*|\\.[0-9]+)\$"))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun stringToBitmap(string: String): Bitmap? {
        val imageBytes = Base64.getDecoder().decode(string)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun bitmapToString(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.getEncoder().encodeToString(byteArray)
    }
}