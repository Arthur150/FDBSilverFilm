package com.example.fdbsilverfilm.model

import java.io.Serializable

data class Meta(
    val focal: Float,
    val opening: Float,
    val time: Double,
    val lens: String
) : Serializable
