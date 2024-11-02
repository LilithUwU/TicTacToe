package com.example.toctactoe.view

import android.R
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.mutableStateOf
import com.example.toctactoe.viewmodel.PlayersViewModel

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.toctactoe.Constants
import com.example.toctactoe.model.Players
import java.time.Instant
import java.util.Date

@Composable
fun CustomDialogComposable(
    setShowDialog: (Boolean) -> Unit,
    resultLauncher: ActivityResultLauncher<Intent>,
    viewModel: PlayersViewModel
) {
    Dialog(
        onDismissRequest = { setShowDialog(false) },
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        DialogTitle(setShowDialog)
                    }
                    Row(
                        modifier = Modifier.wrapContentSize(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        DialogContent(resultLauncher, setShowDialog, viewModel)
                    }
                }
            }
        }
    }
}

@Composable
private fun DialogContent(
    resultLauncher: ActivityResultLauncher<Intent>,
    setShowDialog: (Boolean) -> Unit,
    viewModel: PlayersViewModel
) {
    var player1 by remember { mutableStateOf("") }
    var player2 by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column {
        PlayerInputFieldDialog(
            name = player1,  
            value = { player1 = it },  
            hint = "Player 1"
        )
        PlayerInputFieldDialog(
            name = player2,  
            value = { player2 = it }, 
            hint = "Player 2"
        )
        Button(
            onClick = {
                viewModel.addPlayers(Players(
                    player1 = player1,
                    player2 = player2,
                    gamesPlayed = "0",
                    player1Score = "0",
                    player2Score = "0",
                    id = 0,
                    lastPlayed = Date.from(Instant.now()),
                ))
                val text1 = player1.toString().ifEmpty { Constants.INTENT_PLAYER1_NAME }
                val text2 = player2.toString().ifEmpty { Constants.INTENT_PLAYER2_NAME }
                val intent = Intent(context, GameActivity::class.java).apply {
                    putExtra(Constants.INTENT_PLAYER1_NAME, text1)
                    putExtra(Constants.INTENT_PLAYER2_NAME, text2)
                }
                resultLauncher.launch(intent)
                setShowDialog(false)
            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Start")
        }
    }
}


@Composable
private fun DialogTitle(setShowDialog: (Boolean) -> Unit) {
    Text(
        text = "Set player names",
        style = TextStyle(
            fontSize = 24.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold
        )
    )
    Icon(
        imageVector = Icons.Filled.Cancel,
        contentDescription = "",
        tint = colorResource(R.color.darker_gray),
        modifier = Modifier
            .width(30.dp)
            .height(30.dp)
            .clickable { setShowDialog(false) }
    )
}

@Composable
fun PlayerInputFieldDialog(
    name: String,
    value: (String) -> Unit,
    hint: String
) {
    OutlinedTextField(
        modifier = Modifier
            .wrapContentSize()
            .padding(5.dp),
        value = name,
        onValueChange = value,
        placeholder = { Text(hint) },
        maxLines = 1,
    )
}
