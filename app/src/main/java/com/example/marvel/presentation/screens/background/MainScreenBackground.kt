package com.example.marvel.presentation.screens.background

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import com.example.marvel.R
import com.example.marvel.presentation.MainViewModel

@Composable
fun MainScreenBackground(viewModel: MainViewModel) {


    Card() {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            val shapeLeft = GenericShape { size: Size, _ ->
                val width = size.width
                val height = size.height
                moveTo(0f, height)
                lineTo(0f, 0f)
                lineTo(width, 0f)
                lineTo(width, (height / 2.5).toFloat())
                close()
            }

            val shapeRight = GenericShape { size: Size, _ ->
                val width = size.width
                val height = size.height
                moveTo(width, (height / 2.5).toFloat())
                lineTo(width, height)
                lineTo(0f, height)
                close()
            }

            val modifierLeft = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    clip = true
                    shape = shapeLeft
                }


            val modifierRight = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    clip = true
                    shape = shapeRight
                }

            Box(
                modifier = modifierLeft
                    .background(colorResource(R.color.grey))
            )

            Box(
                modifier = modifierRight
                    .background(viewModel.triangleColor)
            )
        }
    }
}