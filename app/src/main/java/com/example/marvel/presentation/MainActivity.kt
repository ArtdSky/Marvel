package com.example.marvel.presentation

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.marvel.MarvelApplication
import com.example.marvel.presentation.navigation.CardsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            val navController = rememberNavController()

            val context = LocalContext.current
            val viewModel: MainViewModel by viewModels( )

            CardsNavHost(navController = navController, viewModel = viewModel)
        }
    }
}
