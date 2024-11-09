package com.example.tictactoe.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.tictactoe.Constants
import com.example.tictactoe.Constants.INTENT_PLAYER1_NAME
import com.example.tictactoe.Constants.INTENT_PLAYER2_NAME
import com.example.tictactoe.Constants.TAG
import com.example.tictactoe.GameUtils
import com.example.tictactoe.R
import com.example.tictactoe.databinding.ActivitySecondBinding
import com.example.tictactoe.model.Players
import com.example.tictactoe.viewmodel.GameActivityViewModel
import java.time.Instant
import java.util.Date

class GameActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
    }
    private lateinit var player1Name: String
    private lateinit var player2Name: String
    private lateinit var gameUtils: GameUtils
    private lateinit var players: Players

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val gameViewModel = ViewModelProvider(this)[GameActivityViewModel::class.java]
        initPlayersNameByIntent(gameViewModel)
        binding.btnRestart.setOnClickListener { gameUtils.restartGame() }
        binding.backBtn.setOnClickListener { finish() }
    }


    private fun initPlayersNameByIntent(gameViewModel: GameActivityViewModel) {
        when (intent.getStringExtra(Constants.COMING_FROM)) {
            Constants.INTENT_EXTRA_NEW_GAME -> {
                player1Name = intent.getStringExtra(INTENT_PLAYER1_NAME).toString()
                player2Name = intent.getStringExtra(INTENT_PLAYER2_NAME).toString()
                players = Players(
                    id = 0,
                    player1 = player1Name,
                    player2 = player2Name,
                    gamesPlayed = "0",
                    player1Score = "0",
                    player2Score = "0",
                    lastPlayed = Date.from(Instant.now()),
                )
                initializeGameUtils(gameViewModel)
            }
//todo savPlayers(boolean-boolean, players) if true->add, else update
//  migrate db, change field types
            Constants.INTENT_EXTRA_HISTORY -> {
                val playerId = intent.getIntExtra(Constants.PLAYER_ID, 0)
                player1Name = ""
                player2Name = ""

                gameViewModel.getPlayer(playerId) { player ->
                    Log.d(TAG, "getPlayer: $player")
                    player1Name = player.player1
                    player2Name = player.player2
                    players = player
                    initializeGameUtils(gameViewModel)
                }

            }

        }
    }


    private fun initializeGameUtils(gameViewModel: GameActivityViewModel) {
        gameUtils = GameUtils(this, binding, players, gameViewModel)
        gameUtils.setListenersForCells(gameUtils.btnClickListener())

        Toast.makeText(this, "$player1Name & $player2Name", Toast.LENGTH_SHORT).show()
        binding.hint.text = getString(R.string.it_s_your_turn, player1Name)
    }

}