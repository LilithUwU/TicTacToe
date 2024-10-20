package com.example.toctactoe.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope
import com.example.toctactoe.MainApplication
import com.example.toctactoe.model.Players

import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date


class PlayersViewModel : ViewModel() {

    val playersDao = MainApplication.Companion.playersDatabase.getPlayersDao()
    val playersList : LiveData<List<Players>> = playersDao.getAllPlayers()

    @RequiresApi(Build.VERSION_CODES.O)
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

    fun deletePlayers(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            playersDao.deletePlayers(id)
        }
    }
}