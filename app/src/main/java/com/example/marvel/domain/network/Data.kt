package com.example.marvel.domain.network

import com.squareup.moshi.Json


data class Data {
    @Json(name = "results")
    var results: List<Result>? = null
}