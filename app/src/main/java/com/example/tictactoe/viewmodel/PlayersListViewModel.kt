package com.example.tictactoe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tictactoe.MainApplication
import com.example.tictactoe.model.Players
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlayersListViewModel  : ViewModel() {
    val playersDao = MainApplication.Companion.playersDatabase.getPlayersDao()
    val playersList : LiveData<List<Players>> = playersDao.getAllPlayers()

    fun deletePlayers(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            playersDao.deletePlayers(id)
        }
    }

}