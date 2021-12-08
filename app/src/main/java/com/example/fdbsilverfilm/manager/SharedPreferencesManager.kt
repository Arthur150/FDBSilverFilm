package com.example.fdbsilverfilm.manager

import android.content.Context
import android.content.SharedPreferences
import com.example.fdbsilverfilm.model.Meta
import com.example.fdbsilverfilm.model.Picture
import com.google.gson.Gson

object SharedPreferencesManager {
    private const val CURRENT_META = "current_meta"
    private const val CURRENT_FILM = "current_film"

    fun saveCurrentMeta(context: Context, picture: Picture) {
        val gson = Gson()
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(CURRENT_META, 0)
        val editor = sharedPreferences.edit()

        editor.clear()

        val json = gson.toJson(picture.meta)

        editor.putString(CURRENT_META, json)

        editor.apply()
    }

    fun loadCurrentMeta(context: Context): Meta {
        val gson = Gson()
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(CURRENT_META, 0)

        val json = sharedPreferences.getString(CURRENT_META, "")

        return gson.fromJson(json, Meta::class.java)
    }

    fun saveCurrentFilm(context: Context, filmId: Int) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(CURRENT_FILM, 0)
        val editor = sharedPreferences.edit()

        editor.clear()

        editor.putInt(CURRENT_FILM, filmId)

        editor.apply()
    }

    fun loadCurrentFilm(context: Context): Int {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(CURRENT_FILM, 0)

        return sharedPreferences.getInt(CURRENT_FILM, -1)

    }
}