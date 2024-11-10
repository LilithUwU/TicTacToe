package com.example.tictactoe.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.tictactoe.Constants
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var player1Score = 0
    private var player2Score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            Surface(
                modifier=Modifier.fillMaxSize(),
                color=MaterialTheme.colorScheme.background
            ){
                MainActivityScreen()
            }
        }
    }
    @SuppressLint("SetTextI18n")
    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == 100) {
                val data: Intent? = result.data
                player1Score += data!!.getIntExtra(Constants.INTENT_PLAYER1_SCORE, 0)
                player2Score += data.getIntExtra(Constants.INTENT_PLAYER2_SCORE, 0)
                binding.scoreTv.text = "$player1Score - $player2Score"
            }
        }


    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
    }

}
