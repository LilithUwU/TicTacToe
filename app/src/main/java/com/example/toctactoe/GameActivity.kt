package com.example.toctactoe

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.toctactoe.databinding.ActivitySecondBinding

class GameActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
    }
    private var player1Score = 0
    private var player2Score = 0
    private var count = 0
    private var index = ""
    private var sign = ""
    private lateinit var array: Array<Array<String>>
    private lateinit var player1: String
    private lateinit var player2: String
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
         player1 = intent.getStringExtra("player1").toString()
         player2 = intent.getStringExtra("player2").toString()

        Toast.makeText(this, "$player1 & $player2", Toast.LENGTH_SHORT).show()
         array = arrayOf(
            arrayOf(".", ".", "."),
            arrayOf(".", ".", "."),
            arrayOf(".", ".", ".")
        )

        binding.hint.text = "$player1 it's your turn"
        val buttonClickListener = View.OnClickListener { view ->
            if (checkResultForTicTacToe(array, player1, player2).second)
                enableBtns(false)
            if (count % 2 == 0 && count != 8) {
                sign = "x"
                binding.hint.text = "$player2 it's your turn"
            } else if (count == 8) {
                binding.hint.text = "It's a draw"
            } else {
                sign = "o"
                binding.hint.text = "$player1 it's your turn"
            }
            when (view.id) {
                R.id.p00 -> {
                    handleClick(0, 0, sign, "00" , findViewById(R.id.p00))
                }

                R.id.p01 -> {
                    handleClick(0, 1, sign, "12" , findViewById(R.id.p01))
                }

                R.id.p02 -> {
                    handleClick(0, 2, sign, "02" , findViewById(R.id.p02))
                }

                R.id.p10 -> {
                    handleClick(1, 0, sign, "10" , findViewById(R.id.p10))
                }

                R.id.p11 -> {
                    handleClick(1, 1, sign, "11" , findViewById(R.id.p11))
                }

                R.id.p12 -> {
                    handleClick(1, 2, sign, "12" , findViewById(R.id.p12))
                }

                R.id.p20 -> {
                    handleClick(2, 0, sign, "20" , findViewById(R.id.p20))
                }

                R.id.p21 -> {
                    handleClick(2, 1, sign, "21" , findViewById(R.id.p21))
                }

                R.id.p22 -> {
                    handleClick(2, 2, sign, "22" ,findViewById(R.id.p22))
                }
            }
        }
        setListeners(buttonClickListener)
        binding.backBtn.setOnClickListener {
            val resultIntent = Intent(this, MainActivity::class.java)
            resultIntent.putExtra("player1Score", player1Score)
            resultIntent.putExtra("player2Score", player2Score)
            resultIntent.putExtra("player1", player1)
            resultIntent.putExtra("player2", player2)
            setResult(100, resultIntent)
            finish()
        }
    }

    private fun handleClick(i: Int, j: Int, sign: String, s: String, view: View) {
        array[i][j] = sign
        (view as TextView).text = sign
        index = s
        count++
        view.isEnabled = false
        checkResultForTicTacToe(array, player1, player2)
    }

    @SuppressLint("SetTextI18n")
    fun checkResultForTicTacToe(
        arr: Array<Array<String>>,
        player1: String,
        player2: String
    ): Pair<String, Boolean> {
        for (i in arr.indices) {
            if (arr[i][0] == arr[i][1] && arr[i][1] == arr[i][2]) {
                if (arr[i][0] == "x") {
                    binding.hint.text = "$player1 won"
                    player1Score++
                    enableBtns(false)
                    return Pair(player1, true)
                } else if (arr[i][0] == "o") {
                    binding.hint.text = "$player2 won"
                    player2Score++
                    enableBtns(false)
                    return Pair(player2, true)
                }
            }

            if (arr[0][i] == arr[1][i] && arr[1][i] == arr[2][i]) {
                if (arr[0][i] == "x") {
                    binding.hint.text = "$player1 won"
                    player1Score++
                    enableBtns(false)
                    return Pair(player1, true)
                } else if (arr[0][i] == "o") {
                    binding.hint.text = "$player2 won"
                    player2Score++
                    enableBtns(false)
                    return Pair(player2, true)
                }
            }
        }
        if (arr[0][0] == arr[1][1] && arr[1][1] == arr[2][2]) {
            if (arr[0][0] == "x") {
                binding.hint.text = "$player1 won"
                enableBtns(false)
                player1Score++
                return Pair(player1, true)
            } else if (arr[0][0] == "o") {
                player2Score++
                binding.hint.text = "$player2 won"
                enableBtns(false)
                return Pair(player2, true)
            }
        }
        if (arr[0][2] == arr[1][1] && arr[1][1] == arr[2][0]) {
            if (arr[0][2] == "x") {
                player1Score++
                binding.hint.text = "$player1 won"
                enableBtns(false)
                return Pair(player1, true)
            } else if (arr[0][2] == "o") {
                player2Score++
                binding.hint.text = "$player2 won"
                enableBtns(false)
                return Pair(player2, true)
            }
        } else return Pair("Draw", false)
        return Pair("Error", false)
    }

    private fun enableBtns(state: Boolean ) {
        listOf(
            binding.p00, binding.p01, binding.p02,
            binding.p10, binding.p11, binding.p12,
            binding.p20, binding.p21, binding.p22
        ).forEach { it.isEnabled = state }
    }

    private fun setListeners(buttonClickListener: View.OnClickListener) {
        listOf(
            binding.p00, binding.p01, binding.p02,
            binding.p10, binding.p11, binding.p12,
            binding.p20, binding.p21, binding.p22
        ).forEach { it.setOnClickListener(buttonClickListener) }
    }


}




