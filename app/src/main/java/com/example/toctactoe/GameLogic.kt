package com.example.toctactoe

interface GameLogic {
    fun restartGame()
    fun checkGameResult(): Pair<String, Boolean>
    fun saveGameScore()
}