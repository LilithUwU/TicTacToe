package com.example.toctactoe

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.toctactoe.db.PlayersDatabase

class MainApplication: Application() {
    companion object {
        lateinit var playersDatabase: PlayersDatabase
    }

    override fun onCreate() {
        super.onCreate()
        playersDatabase = Room.databaseBuilder(
            applicationContext,
            PlayersDatabase::class.java,
            PlayersDatabase.DB_NAME
        ).build()
    }

}