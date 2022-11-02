package com.example.marvel.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object MarvelApi {
    private const val BASE_URL =
        "https://gateway.marvel.com/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()


    val retrofitService: CharactersMarvelApiService by lazy { retrofit.create(CharactersMarvelApiService::class.java) }
}