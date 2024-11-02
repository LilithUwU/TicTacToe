package com.example.tictactoe.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.ViewModelProvider
import com.example.tictactoe.viewmodel.PlayersViewModel
import androidx.compose.ui.Modifier


class ScoresActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val playersViewModel = ViewModelProvider(this)[PlayersViewModel::class.java]
        setContent{
            Surface(
                modifier=Modifier.fillMaxSize(),
                color=MaterialTheme.colorScheme.background
            ){
                    PlayersListPage(playersViewModel)
            }
        }

    }
}