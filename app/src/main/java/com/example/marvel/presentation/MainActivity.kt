package com.example.marvel.presentation

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.marvel.CardsNavHost

class MainActivity : ComponentActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            val navController = rememberNavController()

            val context = LocalContext.current
            val viewModel: MainViewModel = viewModel(
                factory = MainViewModelFactory(context.applicationContext as Application)
            )

            CardsNavHost(navController = navController, viewModel = viewModel)
        }
    }
}
