package com.example.tictactoe.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.ViewModelProvider
import androidx.compose.ui.Modifier
import com.example.tictactoe.viewmodel.PlayersListViewModel


class ScoresActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val playersViewModel=ViewModelProvider(this)[PlayersListViewModel::class.java]
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