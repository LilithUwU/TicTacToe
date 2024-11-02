package com.example.tictactoe.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
/*Table structure*/
@Entity
data class Players (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var player1: String,
    var player2: String,
    var lastPlayed: Date,
    var gamesPlayed: String,
    var player1Score: String,
    var player2Score: String
)
