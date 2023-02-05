package com.example.marvel.di

import com.example.marvel.data.network.ApiInterceptor
import com.example.marvel.data.network.MarvelApiService
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Named("BASE_URL")
    fun provideBaseUrl(): String = "https://gateway.marvel.com/"

    @Provides
    @Singleton
    fun provideRetrofit(
        @Named("BASE_URL") baseUrl : String
    ) : Retrofit{

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(ApiInterceptor())
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(ResultCallAdapterFactory.create())
            .build()
    }

    @Provides
    fun provideCharactersMarvelApiService(retrofit : Retrofit) : MarvelApiService {
        return retrofit.create(MarvelApiService::class.java)
    }

}