package com.example.fdbsilverfilm.model

import android.location.Location
import java.io.Serializable

data class Meta(
    val focal: Float = 0.0f,
    val opening: Float = 0.0f,
    val time: Double = 0.0,
    val mode: String,
    val coordinates: Location?,
    val lens: String
) : Serializable
