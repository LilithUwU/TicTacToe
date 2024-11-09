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
import com.example.tictactoe.viewmodel.GameActivityViewModel

class GameActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
    }
    private lateinit var player1Name: String
    private lateinit var player2Name: String
    private lateinit var gameUtils: GameUtils

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val gameViewModel = ViewModelProvider(this)[GameActivityViewModel::class.java]
        initPlayersNameByIntent(gameViewModel)
        gameUtils = GameUtils(this, binding, player1Name, player2Name, gameViewModel)
        gameUtils.setListenersForCells(gameUtils.btnClickListener())

        binding.btnRestart.setOnClickListener { gameUtils.restartGame() }
        Toast.makeText(this, "$player1Name & $player2Name", Toast.LENGTH_SHORT).show()
        binding.hint.text = getString(R.string.it_s_your_turn, player1Name)
        binding.backBtn.setOnClickListener { finish() }
    }


    private fun initPlayersNameByIntent(gameViewModel: GameActivityViewModel) {
        when (intent.getStringExtra(Constants.COMING_FROM)) {
            Constants.INTENT_EXTRA_NEW_GAME -> {
                player1Name = intent.getStringExtra(INTENT_PLAYER1_NAME).toString()
                player2Name = intent.getStringExtra(INTENT_PLAYER2_NAME).toString()
            }

            Constants.INTENT_EXTRA_HISTORY -> {
                val playerId = intent.getIntExtra(Constants.PLAYER_ID, 0)
                player1Name = ""
                player2Name = ""

                gameViewModel.getPlayer(playerId) { player ->
                    Log.d(TAG, "getPlayer: $player")
                    Log.d(TAG, "getPlayer: $player1Name")
                    Log.d(TAG, "getPlayer: $player2Name")
                    player1Name = player.player1
                    player2Name = player.player2
                    Log.d(TAG, "getPlayer: $player1Name")
                    Log.d(TAG, "getPlayer: $player2Name")

                }
            }

            else -> {
                player1Name = "player1"
                player2Name = "player2"
            }
        }
    }

}