package com.example.toctactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch


class PlayersViewModel : ViewModel() {
    val playersDao = MainApplication.playersDatabase.getPlayersDao()
    val playersList: LiveData<List<PlayersEntity>> = playersDao.getAllPlayers()

    fun addPlayers(playerNames: String) {
        viewModelScope.launch(Dispatchers.IO) {
//            playersDao.addPlayers(player)
        }

    }

    fun deletePlayers(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            playersDao.deletePlayers(id)
        }
    }
}

