package com.example.toctactoe.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModelProvider
import com.example.toctactoe.Constants
import com.example.toctactoe.databinding.ActivityMainBinding
import com.example.toctactoe.viewmodel.PlayersViewModel

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
        val showDialog = mutableStateOf(false)
        val playersViewModel = ViewModelProvider(this)[PlayersViewModel::class.java]

        binding.composeView.setContent {
            if (showDialog.value) {
                CustomDialogComposable(setShowDialog = { isVisible ->
                    showDialog.value = isVisible
                }, resultLauncher, playersViewModel)
            }
        }
        binding.btnNewGame.setOnClickListener {
            showDialog.value = true
        }



//        binding.btnNewGame.setOnClickListener {
//            val intent = Intent(this, GameActivity::class.java)
//            player1 = binding.player1.text.toString()
//            player2 = binding.player2.text.toString()
//            if (player1 == "") player1 = Constants.INTENT_PLAYER1_NAME
//            if (player2 == "") player2 = Constants.INTENT_PLAYER2_NAME
//            intent.putExtra(Constants.INTENT_PLAYER1_NAME, player1)
//            intent.putExtra(Constants.INTENT_PLAYER2_NAME, player2)
//            resultLauncher.launch(intent)
//        }
        binding.scores.setOnClickListener{
            resultLauncher.launch(Intent(this, ScoresActivity::class.java))
        }
    }
    @SuppressLint("SetTextI18n")
    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == 100) {
                val data: Intent? = result.data
                player1Score += data!!.getIntExtra(Constants.INTENT_PLAYER1_SCORE, 0)
                player2Score += data.getIntExtra(Constants.INTENT_PLAYER2_SCORE, 0)
//                binding.player1.setText(data.getStringExtra(Constants.INTENT_PLAYER1_NAME).orEmpty())
//                binding.player2.setText(data.getStringExtra(Constants.INTENT_PLAYER2_NAME).orEmpty())
                binding.scoreTv.text = "$player1Score - $player2Score"
            }
        }


    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
    }

}
