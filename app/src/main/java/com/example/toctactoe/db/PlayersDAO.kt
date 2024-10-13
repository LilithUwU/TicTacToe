package com.example.toctactoe.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.toctactoe.PlayersEntity


@Dao
interface PlayersDAO {
    @Query("SELECT * FROM PlayersEntity")
    fun getAllPlayers(): LiveData<List<PlayersEntity>>

    @Insert
    fun addPlayers(playerNames: PlayersEntity)

    @Query("DELETE FROM PlayersEntity where id = :id")
    fun deletePlayers(id: Int)

//    fun updatePlayers(playersEntity: PlayersEntity)

}