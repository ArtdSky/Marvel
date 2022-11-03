package com.example.marvel.presentation.screens.home.slider

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.marvel.data.network.models.Result
import com.example.marvel.presentation.MainViewModel
import com.example.marvel.presentation.navigation.NavRoute


@Composable
fun CharacterCard(
    navController: NavHostController,
    viewModel: MainViewModel,
    character: Result,
    enableResize: Boolean,
) {

//    val url = URL("${character.thumbnail.path}.${character.thumbnail.extension}")
//    var image : Bitmap
//    val bitmap = remember {
//        BitmapFactory.decodeStream(url.openConnection().getInputStream())
//    }
//    val palette = remember {
//        Palette.from(bitmap).generate()
//    }
//    val darkVibrantSwatch = palette.darkVibrantSwatch
//    viewModel.setColor( darkVibrantSwatch?.let { Color(it.rgb) } )
//
//    LaunchedEffect(character.id){
//        val bitmap = remember {
//            BitmapFactory.decodeStream(url.openConnection().getInputStream())
//        }
//    }

    val sizeState by animateDpAsState(if (enableResize) 580.dp else 400.dp)
    val paddingState by animateDpAsState(if (enableResize) 80.dp else 180.dp)
    Box(
        modifier = Modifier
            .padding(
                top = paddingState, // 80 180
                start = 15.dp,
                end = 15.dp
            )
            .height(
                sizeState // 580 400
            )
            .width(400.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                navController.navigate(NavRoute.CharacterDetail.route + "/${character.id}")
            }

    ) {

        AsyncImage(
            model = "${character.thumbnail.path}.${character.thumbnail.extension}",
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
        )
        Box(
            contentAlignment = Alignment.BottomStart,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = character.name,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                fontSize = 40.sp,
                modifier = Modifier.padding(80.dp)
            )

        }
    }
}