package com.example.toctactoe.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.toctactoe.PlayersViewModel
import com.example.toctactoe.R
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
//                MainActivityScreen(playersViewModel) //fortest
                    PlayersListPage(playersViewModel)
            }
        }

    }
}