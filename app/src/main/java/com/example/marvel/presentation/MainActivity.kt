package com.example.marvel.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.compose.rememberNavController
import com.example.marvel.CardsNavHost

class MainActivity : ComponentActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val viewModel by viewModels<MainViewModel>()

            CardsNavHost(navController = navController, viewModel = viewModel)
        }
    }
}
