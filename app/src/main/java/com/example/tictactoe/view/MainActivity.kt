package com.example.tictactoe.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier

class MainActivity : AppCompatActivity() {
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
    override fun onResume() {
        super.onResume()
    }

}
