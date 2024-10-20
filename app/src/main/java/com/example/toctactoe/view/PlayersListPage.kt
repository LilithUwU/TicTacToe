package com.example.toctactoe.view


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toctactoe.model.Players
import com.example.toctactoe.viewmodel.PlayersViewModel
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date
import java.util.Locale
import com.example.toctactoe.R

@Composable
fun PlayersListPage(viewModel: PlayersViewModel){
    val playersList by viewModel.playersList.observeAsState()
    var player1 by remember { mutableStateOf("") }
    var player2 by remember { mutableStateOf("") }
    var gamesPlayed by remember { mutableStateOf("") }
    var player1Score by remember { mutableStateOf("") }
    var player2Score by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(8.dp)
    ){
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(8.dp)
            , verticalArrangement = Arrangement.Center
        ){
            //Add edit text and button
            PlayerInputField(name = player1, value = { player1 = it }, hint = "Player 1")
            PlayerInputField(name = player2, value = { player2 = it }, hint = "Player 2")
            PlayerInputField(name = gamesPlayed, value = { gamesPlayed = it }, hint = "Games played")
            PlayerInputField(name = player1Score, value = { player1Score = it }, hint = "Player1 Score")
            PlayerInputField(name = player2Score, value = { player2Score = it }, hint = "Player2 Score")

            Button(onClick = {
                viewModel.addPlayers(Players(
                    player1 = player1,
                    player2 = player2,
                    gamesPlayed = gamesPlayed,
                    player1Score = player1Score,
                    player2Score = player2Score,
                    id = 0,  // if auto-generated, set a default value
                    lastPlayed = Date.from(Instant.now())  // set the current time
                ))
                player1 = ""
                player2 = ""
                gamesPlayed=""
                player1Score = ""
                player2Score = ""
            }) {
                Text(text = "Add")
            }


            playersList?.let {
                LazyColumn(
                    content = {
                        itemsIndexed(it){index: Int, item: Players ->
                            PlayersItem(item = item, onDelete = {
                                viewModel.deletePlayers(item.id)
                            })
                        }
                    }
                )
            }?: Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "No items yet",
                fontSize = 16.sp
            )
        }
    }
}


@Composable
fun PlayersItem(item : Players,onDelete : ()-> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = SimpleDateFormat("HH:mm:aa, dd/mm", Locale.ENGLISH).format(item.lastPlayed), fontSize = 12.sp, color = Color.LightGray)
            Text(text = item.player1, fontSize = 20.sp, color = Color.White)
            Text(text = item.player2, fontSize = 20.sp, color = Color.White)
            Text(text = item.gamesPlayed, fontSize = 20.sp, color = Color.White)
            Text(text = item.player1Score, fontSize = 20.sp, color = Color.White)
            Text(text = item.player2Score, fontSize = 20.sp, color = Color.White)
        }
        IconButton(onClick = onDelete) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_delete_24),
                contentDescription = "Delete",
                tint = Color.White
            )
        }
    }
}
@Composable
fun PlayerInputField(
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
        placeholder = { Text(hint)},
        maxLines = 1,
    )
}






