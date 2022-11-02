package com.example.marvel.data.network

import com.example.marvel.data.network.models.Characters
import com.example.marvel.presentation.utils.md5Generator
import com.example.marvel.presentation.utils.pub
import com.example.marvel.presentation.utils.text
import com.example.marvel.presentation.utils.ts
import retrofit2.http.GET

//?ts=$ts&apikey=$pub&hash=$md5
interface CharactersMarvelApiService {
    @GET("/v1/public/characters?ts=$ts&apikey=$pub&hash=$testmd5")
    suspend fun getCharacters(): Characters
}


val md5 = md5Generator(text)
const val testmd5 = "9b2ebe44572b4c206a6bb5f379f10729"



