package com.example.marvel.presentation.screens.character.detail

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.marvel.data.CharactesData
import com.example.marvel.domain.model.Character
import com.example.marvel.presentation.navigation.NavRoute

@Composable
fun CharacterDetail(navController: NavHostController, characterId: String?) {
    val characters = CharactesData.charactersData
    val hero: Character? = characters.find { it.id == characterId?.toInt() }

    Card(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = hero?.link,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Box(
            modifier = Modifier
                .padding(8.dp)
                .size(100.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(30.dp)
                    .clickable { navController.popBackStack()  }

            )
        }


        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
        ) {
            hero?.name?.let {
                Text(
                    text = it,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 40.sp,
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp)
                )
            }
            hero?.description?.let {
                Text(
                    text = it,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 30.sp,
                    modifier = Modifier.padding(20.dp)
                )
            }
        }
    }

}




