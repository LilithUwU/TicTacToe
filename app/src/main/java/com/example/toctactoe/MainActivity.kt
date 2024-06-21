package com.example.toctactoe

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.toctactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var player1 = ""
    private var player2 = ""
    private var player1Score = 0
    private var player2Score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.start.setOnClickListener {
            player1 = binding.player1.text.toString()
            player2 = binding.player2.text.toString()

            if (player1 == "") player1 = "player1"
            if (player2 == "") player2 = "player2"
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("player1", player1)
            intent.putExtra("player2", player2)
            resultLauncher.launch(intent)

        }
    }


    @SuppressLint("SetTextI18n")
    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == 100) {
                val data: Intent? = result.data

                player1Score += data!!.getIntExtra("player1Score", 0)
                player2Score += data.getIntExtra("player2Score", 0)
                binding.player1.setText(data.getStringExtra("player1").orEmpty())
                binding.player2.setText(data.getStringExtra("player2").orEmpty())
                binding.scoreTv.text = "$player1Score-$player2Score"
            }
        }


    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()


    }

}
