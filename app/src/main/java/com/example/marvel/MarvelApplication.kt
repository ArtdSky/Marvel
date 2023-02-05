package com.example.marvel

import android.app.Application
import com.example.marvel.data.local.MarvelDatabase
import com.example.marvel.data.local.MarvelRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MarvelApplication : Application()

//    private val database by lazy { MarvelDatabase.getDatabase(this) }
//    val repository by lazy { MarvelRepository(database.marvelDao()) }

