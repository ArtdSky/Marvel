package com.example.marvel.presentation.screens.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.marvel.presentation.MainViewModel

@Composable
fun CharacterDetail(
    navController: NavHostController,
    characterId: String?,
    viewModel: MainViewModel
) {

    viewModel.getCharacter(characterId)
    val state by viewModel.viewState.collectAsState()
    val character = state.character

    character?.let {
        Card(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = "${it.thumbnail.path}.${it.thumbnail.extension}",
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
                        .clickable { navController.popBackStack() }
                )
            }
            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = it.name,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Left,
                    color = Color.White,
                    fontSize = 40.sp,
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp)
                )
                Text(
                    text = it.description.ifEmpty { "I am ${it.name}" },
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Left,
                    color = Color.White,
                    fontSize = 30.sp,
                    modifier = Modifier.padding(20.dp)
                )
            }
        }
    }
}




