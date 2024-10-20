package com.example.toctactoe.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.toctactoe.model.Players

@Database(entities = [Players::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class PlayersDatabase : RoomDatabase() {
    companion object{
        const val DATABASE_NAME = "players_database"
    }
    abstract fun getPlayersDao() : PlayersDAO
}