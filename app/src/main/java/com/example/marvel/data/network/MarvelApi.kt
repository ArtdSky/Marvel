package com.example.marvel.data.network

import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object MarvelApi {
    private const val BASE_URL =
        "https://gateway.marvel.com/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private var okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(ApiInterceptor())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .build()

    val retrofitCharactersService: CharactersMarvelApiService by lazy {
        retrofit.create(CharactersMarvelApiService::class.java)
    }
}
