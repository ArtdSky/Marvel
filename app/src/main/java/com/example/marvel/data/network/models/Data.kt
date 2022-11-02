package com.example.marvel.data.network.models


data class Characters(
    val data: Data
)

data class Data(
    val results: List<Result>
)

data class Result(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
)

data class Thumbnail(
    val path: String,
    val extension: String
)


