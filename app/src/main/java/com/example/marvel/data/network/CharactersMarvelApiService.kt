package com.example.marvel.data.network

import com.example.marvel.data.network.models.Characters
import retrofit2.http.GET
import retrofit2.http.Path

//?ts=$ts&apikey=$pub&hash=$md5
interface CharactersMarvelApiService {
    @GET("/v1/public/characters/{id}")
    suspend fun getCharacter(@Path("id") id: String?): Result<Characters>

    @GET("/v1/public/characters")
    suspend fun getCharacters(): Result<Characters>
}





