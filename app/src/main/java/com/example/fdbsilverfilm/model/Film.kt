package com.example.fdbsilverfilm.model

import java.io.Serializable

data class Film(
    val id : Int? = null,
    var name : String? = null,
    var brand : String? = null,
    var iso : Int? = null,
    var type : String? = null,
    var nbPoses : Int? = null,
    val pictures : ArrayList<Picture> = ArrayList()
) : Serializable