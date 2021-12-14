package com.example.fdbsilverfilm.model

object Globals {
    const val PICTURE_EXTRA_TAG = "picture"
    const val FILM_EXTRA_TAG = "film"
    const val NOT_FULL_FILTER = 1
    const val ALL_FILTER = 0
    const val MAIN_EXTRA_TAG = "main"
    const val FILM_ID_EXTRA_TAG = "filmId"
    const val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"


    fun regexInt(string: String): Boolean {
        return string.matches(Regex("^[1-9]\\d*\$"))
    }

    fun regexDecimal(string: String): Boolean {
        return string.matches(Regex("^[+-]?([0-9]+\\.?[0-9]*|\\.[0-9]+)\$"))
    }
}