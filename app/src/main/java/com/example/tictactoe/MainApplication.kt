package com.example.tictactoe

import android.app.Application
import androidx.room.Room
import com.example.tictactoe.db.PlayersDatabase

class MainApplication : Application() {
    companion object {
        lateinit var playersDatabase: PlayersDatabase
    }

    override fun onCreate() {
        super.onCreate()
        playersDatabase=Room.databaseBuilder(
            applicationContext,
            PlayersDatabase::class.java,
            PlayersDatabase.DATABASE_NAME
        ).build()
    }
}