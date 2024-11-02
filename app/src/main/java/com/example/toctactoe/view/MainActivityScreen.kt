package com.example.toctactoe.view
import androidx.compose.foundation.Image

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.waterfall
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toctactoe.viewmodel.PlayersViewModel
import com.example.toctactoe.R

//todo redesign
val fontAdlamDisplay = FontFamily(
    Font(R.font.adlam_display, FontWeight.Normal)
)
val fontLemon = FontFamily(
    Font(R.font.lemon, FontWeight.Normal)
)
val showDialog = mutableStateOf(false)

val largeRadialGradient = object : ShaderBrush() {
    override fun createShader(size: Size): Shader {
        val biggerDimension = maxOf(size.height, size.width)
        return RadialGradientShader(
            colors = listOf(Color(0xFF532BE4), Color(0xFF673AB7)),
            center = size.center,
            radius = biggerDimension / 2f,
            colorStops = listOf(0f, 0.95f)
        )
    }
}


@Composable
fun MainActivityScreen(viewModel: PlayersViewModel) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(largeRadialGradient)) { Column(
        modifier = Modifier
            .padding(30.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "TicTacToe",
            fontSize = 50.sp,
            color = Color.White,
            fontFamily = fontLemon,
            modifier = Modifier.padding(bottom = 40.dp),
        )
        Button(
            onClick = { },
            shape = Shapes().small,
            modifier = Modifier
                .wrapContentSize()
                .padding(bottom = 10.dp)
        ) {
            Icon(Icons.Default.Bolt, contentDescription = null)
            Text(
                text = stringResource(R.string.start_new_game),
                fontSize = 25.sp,
                fontFamily = fontAdlamDisplay,

                )
            Icon(Icons.Default.Bolt, contentDescription = null)
        }
        Button(
            onClick = { },
            shape = Shapes().small,
            modifier = Modifier.wrapContentSize()
        ) {
            Text(
                text = stringResource(R.string.game_history),
                fontFamily = fontAdlamDisplay,
                fontSize = 25.sp,
            )
            Icon(Icons.Default.History, contentDescription = null, Modifier.padding(start = 5.dp))

        }
    } }



}

@Preview(showBackground = true)
@Composable
fun PreviewMainActivityScreen() {
    val mockViewModel = PlayersViewModel()
    MainActivityScreen(viewModel = mockViewModel)
}


//possible design of game history page, in progress
@Preview(showBackground = true)
@Composable
fun PreviewScoreItemScreen() {
    val mockViewModel = PlayersViewModel()
    ScoreItem(viewModel = mockViewModel)
}

@Composable
fun ScoreItem(viewModel: PlayersViewModel) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row() {
            StyleOutlinedText("Position")
            StyleOutlinedText("Players")
            StyleOutlinedText("Score")
            StyleOutlinedText("Played Games")
        }//Table names

    }
}
@Composable
fun StyleOutlinedText(text: String){
    Text(
        text = text,
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(0.dp)
            )
            .padding(8.dp)
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewScoreWinnerDialog() {
    val mockViewModel = PlayersViewModel()
    WinnerDialog(viewModel = mockViewModel)
}

@Composable
fun WinnerDialog(viewModel: PlayersViewModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Black.copy(alpha = 0.7f))
        ,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.confetti),
            contentDescription = "Winner Background",
            modifier = Modifier.wrapContentSize(),
        )

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "Congratulations",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = FontFamily(Font(R.font.lemon)),
            )
            Text(
                text = "Martha",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

