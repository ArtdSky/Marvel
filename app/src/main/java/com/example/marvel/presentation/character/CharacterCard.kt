package com.example.marvel.presentation.character

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marvel.domain.model.Character

@Composable
fun CharacterCard(
    character: Character,
    enableResize: Boolean
) {
    val sizeState by animateDpAsState(if (enableResize) 580.dp else 400.dp)
    val paddingState by animateDpAsState(if (enableResize) 80.dp else 180.dp)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = paddingState, // 80 180
                start = 15.dp,
                end = 15.dp
            )
            .height(
                sizeState // 580 400
            )
            .clip(RoundedCornerShape(16.dp))


    ) {
        Image(
            painter = painterResource(id = character.image),
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
                fontSize = 40.sp,
                modifier = Modifier.padding(40.dp)
            )
        }
    }
}