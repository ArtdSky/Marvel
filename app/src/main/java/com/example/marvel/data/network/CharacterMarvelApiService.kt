package com.example.marvel.data.network

import com.example.marvel.data.network.models.Characters
import com.example.marvel.presentation.utils.pub
import com.example.marvel.presentation.utils.testmd5
import com.example.marvel.presentation.utils.ts
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterMarvelApiService {
    @GET("/v1/public/characters/{id}?ts=$ts&apikey=$pub&hash=$testmd5")
    suspend fun getCharacter(@Path("id") id : String?): Characters
}
