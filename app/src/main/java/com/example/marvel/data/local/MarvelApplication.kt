package com.example.marvel.data.local

import android.app.Application

class MarvelApplication : Application() {

    private val database by lazy { MarvelDatabase.getDatabase(this) }
    val repository by lazy { MarvelRepository(database.marvelDao()) }
}
