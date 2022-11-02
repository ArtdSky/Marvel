package com.example.marvel.data.network

import com.example.marvel.data.network.models.Characters
import com.example.marvel.presentation.utils.*
import retrofit2.http.GET

//?ts=$ts&apikey=$pub&hash=$md5
interface CharactersMarvelApiService {
    @GET("/v1/public/characters?ts=$ts&apikey=$pub&hash=$testmd5")
    suspend fun getCharacters(): Characters
}





