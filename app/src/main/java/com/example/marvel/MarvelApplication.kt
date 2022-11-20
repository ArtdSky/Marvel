package com.example.marvel

import android.app.Application
import com.example.marvel.data.local.MarvelDatabase
import com.example.marvel.data.local.MarvelRepository

class MarvelApplication : Application() {

    private val database by lazy { MarvelDatabase.getDatabase(this) }
    val repository by lazy { MarvelRepository(database.marvelDao()) }
}
