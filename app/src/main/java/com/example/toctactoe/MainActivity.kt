package com.example.toctactoe

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.toctactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var player1 = "player1"
    private var player2 = "player2"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.start.setOnClickListener {
            player1 = binding.player1.text.toString()
            player2 = binding.player2.text.toString()

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("player1", player1)
            intent.putExtra("player2", player2)
            startActivity(intent)
        }


    }


}