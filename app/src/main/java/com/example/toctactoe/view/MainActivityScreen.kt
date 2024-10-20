package com.example.toctactoe.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toctactoe.PlayersViewModel
//todo redesign
//val adlamDisplay = FontFamily(
//    Font(R.font.adlam_display, FontWeight.Normal) // Add other weights if needed
//)

@Composable
fun MainActivityScreen(viewModel: PlayersViewModel) {

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(modifier = Modifier.padding(30.dp)) {
//            Text(text = "TicTacToe", fontSize = 30.sp)
            Button(
                onClick = { },
                modifier = Modifier.wrapContentSize()
            ) {
                Text(text = "New Game")
            }
            Button(
                onClick = {  },
                modifier = Modifier.wrapContentSize()
            ) {
                Text(text = "Game History")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainActivityScreen() {
    // Create a mock or default ViewModel for preview purposes
    val mockViewModel = PlayersViewModel() // Add mock data if needed
    MainActivityScreen(viewModel = mockViewModel)
}