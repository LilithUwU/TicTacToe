package com.example.toctactoe.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.toctactoe.Players



@Dao
interface PlayersDAO {
    @Query("SELECT * FROM Players ORDER BY lastPlayed")
    fun getAllPlayers() : LiveData<List<Players>>

    @Insert
    fun addPlayers(players: Players)

    @Query("DELETE FROM Players WHERE id = :id")
    fun deletePlayers(id: Int)


    @Query("SELECT * FROM players WHERE player1 = :player1 AND player2 = :player2 LIMIT 1")
    fun getPlayersByNames(player1: String, player2: String): Players?

    @Update
    fun updatePlayers(player: Players)
}