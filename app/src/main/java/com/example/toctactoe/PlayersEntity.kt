package com.example.toctactoe

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlayersEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int=0,
    var playerNames: String,
    var score: String,
    var playedGamesCount: String
)
