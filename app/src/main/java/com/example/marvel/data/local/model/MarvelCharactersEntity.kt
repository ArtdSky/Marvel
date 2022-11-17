package com.example.marvel.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.marvel.data.network.models.Thumbnail

@Entity(tableName = "marvel_characters")
data class MarvelCharactersEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val description: String,
    @Embedded
    val thumbnail: Thumbnail
)
