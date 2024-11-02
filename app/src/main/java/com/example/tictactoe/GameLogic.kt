package com.example.tictactoe

interface GameLogic {
    fun restartGame()
    fun checkGameResult(): Pair<String, Boolean>
    fun saveGameScore()
}