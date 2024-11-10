package com.example.tictactoe.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.tictactoe.model.Players



@Dao
interface PlayersDAO {
    @Query("SELECT * FROM Players ORDER BY lastPlayed")
    fun getAllPlayers() : LiveData<List<Players>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPlayers(players: Players)

    @Query("DELETE FROM Players WHERE id = :id")
    fun deletePlayers(id: Int)


    @Query("SELECT * FROM players WHERE player1 = :player1 AND player2 = :player2 LIMIT 1")
    fun getPlayersByNames(player1: String, player2: String): Players?

    @Update
    suspend fun updatePlayers(player: Players)

    @Query("SELECT * FROM Players WHERE id = :id")
    fun getPlayer(id: Int): Players

}