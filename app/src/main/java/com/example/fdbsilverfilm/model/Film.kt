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
    val name: String,

    @ColumnInfo(name = "brand")
    val brand: String,

    @ColumnInfo(name = "iso")
    val iso: Int,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "nbrPoses")
    val ndPoses: Int,

    @ColumnInfo(name = "picture")
    val pictures: ArrayList<Picture> = ArrayList()
) : Serializable