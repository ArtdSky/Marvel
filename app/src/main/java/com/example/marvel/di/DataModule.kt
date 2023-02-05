package com.example.marvel.di

import android.content.Context
import com.example.marvel.data.local.MarvelDatabase
import com.example.marvel.data.local.MarvelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideMarvelDatabase(@ApplicationContext context : Context) : MarvelDatabase{
        return  MarvelDatabase.getDatabase(context = context)
    }

    @Provides
    @Singleton
    fun provideMarvelRepository(database: MarvelDatabase) : MarvelRepository{
        return MarvelRepository(database.marvelDao())
    }


}
