package com.example.fdbsilverfilm.model

import java.io.Serializable

data class Film(
    val id : Int,
    var name : String,
    var brand : String,
    var iso : Int,
    var type : String,
    var nbPoses : Int,
    val pictures : ArrayList<Picture> = ArrayList()
) : Serializable