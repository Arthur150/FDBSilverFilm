package com.example.fdbsilverfilm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Films")
data class Film(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "brand")
    var brand: String,

    @ColumnInfo(name = "iso")
    var iso: Int,

    @ColumnInfo(name = "type")
    var type: String,

    @ColumnInfo(name = "nbrPoses")
    var nbPoses: Int,

    @ColumnInfo(name = "cameraName")
    var cameraName: String,

    @ColumnInfo(name = "pictures")
    val pictures: ArrayList<Picture> = ArrayList()
) : Serializable
