package com.example.fdbsilverfilm.database

import androidx.room.TypeConverter
import com.example.fdbsilverfilm.model.Picture
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    //Pictures
    @TypeConverter
    fun savePicture(picture: ArrayList<Picture>): String {
        return Gson().toJson(picture)
    }

    @TypeConverter
    fun restorePicture(picture: String): ArrayList<Picture> {
        return Gson().fromJson(picture, object : TypeToken<ArrayList<Picture>>() {}.type)
    }
}