package com.example.fdbsilverfilm.model

import java.io.Serializable

data class Picture(
    val id: Int,
    val id_film: Int,
    var title: String,
    val date: String,
    val preview: String,
    val meta: Meta
) : Serializable
