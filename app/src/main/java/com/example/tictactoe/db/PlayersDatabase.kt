package com.example.tictactoe.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tictactoe.model.Players

@Database(entities = [Players::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class PlayersDatabase : RoomDatabase() {
    companion object{
        //todo change name to "match-up" when migrating
        const val DATABASE_NAME = "players_database"
    }
    abstract fun getPlayersDao() : PlayersDAO
}