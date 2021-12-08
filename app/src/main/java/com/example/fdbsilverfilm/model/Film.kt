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
    var name: String? = null,

    @ColumnInfo(name = "brand")
    var brand: String? = null,

    @ColumnInfo(name = "iso")
    var iso: Int? = null,

    @ColumnInfo(name = "type")
    var type: String? = null,

    @ColumnInfo(name = "nbrPoses")
    var ndPoses: Int? = null,

    @ColumnInfo(name = "picture")
    val pictures: ArrayList<Picture> = ArrayList()
):Serializable