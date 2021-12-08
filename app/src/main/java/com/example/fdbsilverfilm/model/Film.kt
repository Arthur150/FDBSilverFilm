package com.example.fdbsilverfilm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Films")
data class Film(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "brand")
    val brand: String? = null,

    @ColumnInfo(name = "iso")
    val iso: Int? = null,

    @ColumnInfo(name = "type")
    val type: String? = null,

    @ColumnInfo(name = "nbrPoses")
    val ndPoses: Int? = null,

    @ColumnInfo(name = "picture")
    val pictures: ArrayList<Picture> = ArrayList()
):Serializable