package com.example.toctactoe.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.toctactoe.PlayersEntity

@Database(entities = [PlayersEntity::class], version = 1, exportSchema = false)
abstract class PlayersDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "Players_DB"
    }

    abstract fun getPlayersDao(): PlayersDAO
}