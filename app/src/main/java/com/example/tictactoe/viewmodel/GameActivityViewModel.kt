package com.example.tictactoe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tictactoe.MainApplication
import com.example.tictactoe.model.Players
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class GameActivityViewModel  : ViewModel() {
    val playersDao = MainApplication.Companion.playersDatabase.getPlayersDao()
    val playersList : LiveData<List<Players>> = playersDao.getAllPlayers()

    fun addPlayers(players: Players){
        viewModelScope.launch(Dispatchers.IO){
            playersDao.addPlayers(
                Players(
                    player1 = players.player1,
                    player2 = players.player2,
                    lastPlayed = Date.from(Instant.now()),
                    gamesPlayed = players.gamesPlayed,
                    player1Score = players.player1Score,
                    player2Score = players.player2Score
                )
            )
        }
    }
    fun updateScore(players: Players){
        viewModelScope.launch(Dispatchers.IO){
            playersDao.updatePlayers(Players(
                player1 = players.player1,
                player2 = players.player2,
                lastPlayed = Date.from(Instant.now()),
                gamesPlayed = players.gamesPlayed,
                player1Score = players.player1Score,
                player2Score = players.player2Score
            ))
        }
    }

}