package com.example.marvel

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.SnapOffsets
import dev.chrisbanes.snapper.rememberLazyListSnapperLayoutInfo
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import kotlin.random.Random

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            TopLevel()

        }
    }
}

data class Character(
    var id : Int,
    val name: String,
    val description: String,
    val image: Int
)

@Composable
fun TopLevel() {


    DividedImageCard()
    Logo()
    PreviewCharacter()
}

@Composable
fun CharacterCard(
    character: Character,
    model: MainViewModel = viewModel(),
    enbl : Boolean
) {
    val sizeState by animateDpAsState( if(enbl) 580.dp else 400.dp)
    val paddingState by animateDpAsState( if(enbl) 80.dp else 180.dp )
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


@OptIn(ExperimentalSnapperApi::class)
@Composable
fun CharactersList(
    characters: List<Character>,
    model: MainViewModel = viewModel()
) {
    val lazyListState = rememberLazyListState()
    val layoutInfo = rememberLazyListSnapperLayoutInfo(lazyListState)


    LaunchedEffect(lazyListState.isScrollInProgress) {
        if (!lazyListState.isScrollInProgress) {
            val snappedItem = layoutInfo.currentItem

            model.isEnabled = true
            model.setColor(snappedItem?.index)
            model.snapedItem = snappedItem?.index ?: 0

            Log.d("TAB", snappedItem?.index.toString() )

        } else {
            model.isEnabled = false
        }
    }

    LazyRow(
        state = lazyListState,
        flingBehavior = rememberSnapperFlingBehavior(
            lazyListState = lazyListState,
            snapOffsetForItem = SnapOffsets.Center
        )
    ) {

        items(items = characters, key = { it.id }) { character ->
            if(character.id - 1 == model.snapedItem)
                CharacterCard(character = character, enbl = true)
            else
                CharacterCard(character = character, enbl = false)
        }
    }
}

@Composable
fun PreviewCharacter() {
    Box(
        modifier = Modifier.padding(top = 150.dp)
    ) {
        CharactersList(characters = CharactesData.charactersData)
    }
}

@Composable
fun Logo() {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.marvel),
            contentDescription = null,
            modifier = Modifier
                .size(300.dp, 40.dp)
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
        )
        Text(
            "Choose your hero",
            fontSize = 40.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 60.dp)

        )
    }
}

@Composable
private fun DividedImageCard(model: MainViewModel = viewModel()) {

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
                    .background(colorResource(model.triangleColor))
            )
        }
    }
}

