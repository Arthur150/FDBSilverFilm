package com.example.fdbsilverfilm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Pictures")
data class Picture(
    @PrimaryKey (autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "id_Film")
    val id_film: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "meta")
    val meta: Meta
) : Serializable
