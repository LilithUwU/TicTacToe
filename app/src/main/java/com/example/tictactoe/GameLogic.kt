package com.example.tictactoe

interface GameLogic {
    fun restart()
    fun getStatus(): Pair<String, Boolean>
    fun saveScore()
    fun setEventListeners()
}